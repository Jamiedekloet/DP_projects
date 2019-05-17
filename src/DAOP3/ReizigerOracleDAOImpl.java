package DAOP3;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReizigerOracleDAOImpl implements ReizigerDAO {
    private ArrayList<Reiziger> reizigers = new ArrayList<>();

    public ArrayList<Reiziger> findAll() throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();


        Statement allReizigers = conn.createStatement();
        ResultSet results = allReizigers.executeQuery("SELECT * FROM Reiziger");

        System.out.println(results + "\n");
        while (results.next()) {
            Reiziger newReiziger = new Reiziger();
            newReiziger.setId(results.getInt("REIZIGERID"));
            newReiziger.setNaam(results.getString("VOORLETTERS") + " " +
                    results.getString("TUSSENVOEGSEL") + " " + results.getString("ACHTERNAAM"));
            newReiziger.setGBdatum(results.getDate("GEBORTEDATUM"));

            reizigers.add(newReiziger);
        }

        DAO.closeConnection();
        return reizigers;
    }

    public ArrayList<Reiziger> findByDatum(String GBdatum) throws ParseException, SQLException {
        ArrayList<Reiziger> newReizigers = new ArrayList<>();
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT * FROM Reiziger WHERE geboortedatum = ?"
        );
        preparedStatement.setObject(1, convertToSqlDate(GBdatum));
        ResultSet results = preparedStatement.executeQuery();

        while (results.next()) {
            Reiziger newReiziger = new Reiziger();
            newReiziger.setId(results.getInt("REIZIGERID"));
            newReiziger.setNaam(results.getString("VOORLETTERS") + " " +
                    results.getString("TUSSENVOEGSEL") + " " + results.getString("ACHTERNAAM"));
            newReiziger.setGBdatum(results.getDate("GEBORTEDATUM"));

            newReizigers.add(newReiziger);
        }

        return newReizigers;

    }

    public Reiziger save(Reiziger reiziger) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        String strQuery = "INSERT INTO REIZIGER (REIZIGERID, VOORLETTERS, TUSSENVOEGSEL, ACHTERNAAM, GEBOORTEDATUM) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setInt(1, reiziger.getId());
        pstmt.setString(2, reiziger.getVoorletters());
        pstmt.setString(3, reiziger.getMiddleName());
        pstmt.setString(4, reiziger.getLastName());
//        pstmt.setDate(5, reiziger.getGBdatum());

        return reiziger;
    }

    public Reiziger update(Reiziger reiziger) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        String strQuery = "UPDATE REIZIGER SET (REIZIGERID=?, VOORLETTERS=?, TUSSENVOEGSEL=?, ACHTERNAAM=?, GEBOORTEDATUM=?) WHERE ACHTERNAAM="+reiziger.getNaam()+"AND GEBOORTEDATUM="+reiziger.getGBdatum();
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setInt(1, reiziger.getId());
        pstmt.setString(2, reiziger.getVoorletters());
        pstmt.setString(3, reiziger.getMiddleName());
        pstmt.setString(4, reiziger.getLastName());
//        pstmt.setDate(5, reiziger.getGBdatum());


        return reiziger;
    }

    public boolean delete(Reiziger reiziger) throws SQLException{
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        String strQuery = "DELETE FROM REIZIGER WHERE ACHTERNAAM= ? AND GEBOORTDATUM=?";
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setString(1, reiziger.getNaam());
//        pstmt.setDate(2, reiziger.getGBdatum());

        return true;

    }

    private java.sql.Date convertToSqlDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
        Date parsed = format.parse(date);
        return new java.sql.Date(parsed.getTime());
    }
}

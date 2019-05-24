package DAOP3;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
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
            newReiziger.setCards(OvChipkaartDAOImpl.findByReiziger(newReiziger));

            reizigers.add(newReiziger);
        }

        DAO.closeConnection();
        return reizigers;
    }

    public static Reiziger findByOvChipkaart(OvChipkaart ovChipkaart) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement(
                "SELECT (r.reizigerid, r.voorletters, r.tussenvoegsel, r.achternaam, r.geboortedatum)  FROM Reiziger r, ov_chipkaart o" +
                        "WHERE o.reizigerid = ?"
        );
        preparedStatement.setObject(1, ovChipkaart.getKaartNummer());
        ResultSet results = preparedStatement.executeQuery();

        while (results.next()) {
            Reiziger newReiziger = new Reiziger();
            newReiziger.setId(results.getInt("REIZIGERID"));
            newReiziger.setNaam(results.getString("VOORLETTERS") + " " +
                    results.getString("TUSSENVOEGSEL") + " " + results.getString("ACHTERNAAM"));
            newReiziger.setGBdatum(results.getDate("GEBORTEDATUM"));
            newReiziger.setCards(OvChipkaartDAOImpl.findByReiziger(newReiziger));

            return newReiziger;
        }

        return null;
    }

    public static Reiziger findByProduct(Product product) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

//        PreparedStatement preparedStatement = conn.prepareStatement(
//                "SELECT * FROM Reiziger r, ov_chipkaart o, ov_chipkaart_product p " +
//                        "WHERE p.productnummer = ? AND p.kaartnummer = o.kaartnummer AND o.reizigerid = r.reizigerid"
//        );
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT *" +
            "FROM OV_CHIPKAART_PRODUCT P" +
            "RIGHT JOIN OV_CHIPKAART O ON P.PRODUCTNUMMER = ?" +
            "RIGHT JOIN REIZIGER R ON R.REIZIGERID = O.REIZIGERID");
        preparedStatement.setObject(1, product.getProductNummer());
        ResultSet results = preparedStatement.executeQuery();

        while (results.next()) {
            Reiziger newReiziger = new Reiziger();
            newReiziger.setId(results.getInt("REIZIGERID"));
            newReiziger.setNaam(results.getString("VOORLETTERS") + " " +
                    results.getString("TUSSENVOEGSEL") + " " + results.getString("ACHTERNAAM"));
            newReiziger.setGBdatum(results.getDate("GEBORTEDATUM"));
            newReiziger.setCards(OvChipkaartDAOImpl.findByReiziger(newReiziger));

            return null;
        }

        return null;

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
            newReiziger.setCards(OvChipkaartDAOImpl.findByReiziger(newReiziger));

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

        pstmt.executeQuery();
        conn.close();

        return reiziger;
    }

    public Reiziger update(Reiziger reiziger) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        String strQuery = "UPDATE REIZIGER SET (REIZIGERID=?, VOORLETTERS=?, TUSSENVOEGSEL=?, ACHTERNAAM=?, GEBOORTEDATUM=?) WHERE ACHTERNAAM="+reiziger.getNaam()+"AND REIZIGERID="+reiziger.getId();
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setInt(1, reiziger.getId());
        pstmt.setString(2, reiziger.getVoorletters());
        pstmt.setString(3, reiziger.getMiddleName());
        pstmt.setString(4, reiziger.getLastName());

        pstmt.executeQuery();
        conn.close();

        return reiziger;
    }

    public boolean delete(Reiziger reiziger) throws SQLException{
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        String strQuery = "DELETE FROM REIZIGER WHERE ACHTERNAAM= ? AND REIZIGERID=?";
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setString(1, reiziger.getNaam());
        pstmt.setInt(2, reiziger.getId());

        pstmt.executeQuery();
        conn.close();

        return true;

    }

    private java.sql.Date convertToSqlDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyymmdd");
        Date parsed = format.parse(date);
        return new java.sql.Date(parsed.getTime());
    }

    public static Reiziger findById(int reizigerId) throws SQLException {
        Reiziger reiziger = null;
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM REIZIGER WHERE REIZIGERID = ?"
            );
            preparedStatement.setInt(1, reizigerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            String voorletters, tussenvoegsel, achternaam;
            Date date;
            while (resultSet.next()) {
                reiziger = new Reiziger();
                reiziger.setNaam(resultSet.getString("voorletters"));
//                reiziger.setNaam(resultSet.getString("tussenvoegsel"));
//                reiziger.setNaam(resultSet.getString("achternaam"));
                reiziger.setGBdatum(resultSet.getDate("geboortedatum"));

            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return reiziger;
    }
}

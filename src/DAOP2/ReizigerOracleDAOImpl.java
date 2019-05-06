package DAOP2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        DAO.closeConnection();
        return reizigers;
    }

    public ArrayList<Reiziger> findByDatum(String GBdatum) throws ParseException {
        ArrayList<Reiziger> newReizigers = new ArrayList<>();
        Date gbdate = new SimpleDateFormat("dd/MM/yyyy").parse(GBdatum);
        for (Reiziger reiziger : reizigers) {
            if (reiziger.getGBdatum().compareTo(gbdate) == 0) {
                newReizigers.add(reiziger);
            }
        }
        return newReizigers;

    }

    public Reiziger save(Reiziger reiziger) {
        reizigers.add(reiziger);
        return reiziger;
    }

    public Reiziger update(Reiziger reiziger) {
        int index = -1;

        for (int i = 0; i < reizigers.size(); i++) {
            Reiziger newReiziger = reizigers.get(i);

            if (newReiziger.getId() == reiziger.getId()) {
                index = i;
                break;
            }
        }

        if (index == -1)
            return null;

        reizigers.set(index, reiziger);

        return reiziger;
    }

    public boolean delete(Reiziger reiziger) {
        if (reizigers.contains(reiziger)) {
            reizigers.remove(reiziger);
            return true;
        } else {
            return false;
        }

    }
}

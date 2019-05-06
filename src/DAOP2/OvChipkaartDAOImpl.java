package DAOP2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OvChipkaartDAOImpl implements OvChipkaartDAO {
    public ArrayList<OvChipkaart> findAll() throws SQLException{
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();
        ArrayList<OvChipkaart> cards = new ArrayList<>();

        Statement allReizigers = conn.createStatement();
        ResultSet result = allReizigers.executeQuery("SELECT * FROM OV_CHIPKAART ");

        while (result.next()) {
            OvChipkaart card = new OvChipkaart();
            card.setKaartNummer(result.getInt("kaartnummer"));
            card.setGeldigTot(result.getDate("geldigtot"));
            card.setKlasse(result.getInt("klasse"));
            card.setBalans(result.getInt("saldo"));

            cards.add(card);
        }

        DAO.closeConnection();

        return cards;
    }

    public OvChipkaart getOvChipkaartById(int id) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        Statement getOV = conn.createStatement();
        ResultSet result = getOV.executeQuery("SELECT * FROM OV_CHIPKAART WHERE id ="+id);

        DAO.closeConnection();

        OvChipkaart card = new OvChipkaart();
        while(result.next()) {
            card.setKaartNummer(result.getInt("kaartnummer"));
            card.setGeldigTot(result.getDate("geldigtot"));
            card.setKlasse(result.getInt("klasse"));
            card.setBalans(result.getInt("saldo"));
        }

        return card;
    }

    public ArrayList<OvChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();
        ArrayList<OvChipkaart> cards = new ArrayList<>();

        int id = reiziger.getId();

        Statement allReizigers = conn.createStatement();
        ResultSet result = allReizigers.executeQuery("SELECT * FROM OV_CHIPKAART WHERE reizigerid = "+id);

        while (result.next()) {
            OvChipkaart card = new OvChipkaart();
            card.setKaartNummer(result.getInt("kaartnummer"));
            card.setGeldigTot(result.getDate("geldigtot"));
            card.setKlasse(result.getInt("klasse"));
            card.setBalans(result.getInt("saldo"));

            cards.add(card);
        }

        DAO.closeConnection();

        return cards;
    }
}

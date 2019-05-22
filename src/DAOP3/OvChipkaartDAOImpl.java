package DAOP3;

import java.sql.*;
import java.util.ArrayList;

public class OvChipkaartDAOImpl implements OvChipkaartDAO {
    ArrayList<Product> products;

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
            card.setReiziger(ReizigerOracleDAOImpl.findById(result.getInt("reizigerid")));

            cards.add(card);
        }

        DAO.closeConnection();

        return cards;
    }

    public OvChipkaart getOvChipkaartById(int id) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM OV_CHIPKAART WHERE reizigerid = ?");
        pstmt.setInt(1, id);
        ResultSet result = pstmt.executeQuery();

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

    public static ArrayList<OvChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();
        ArrayList<OvChipkaart> cards = new ArrayList<>();

        int id = reiziger.getId();

        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM OV_CHIPKAART WHERE reizigerid = ?");
        pstmt.setInt(1, id);
        ResultSet result = pstmt.executeQuery();

        while (result.next()) {
            OvChipkaart card = new OvChipkaart();
            card.setKaartNummer(result.getInt("kaartnummer"));
            card.setGeldigTot(result.getDate("geldigtot"));
            card.setKlasse(result.getInt("klasse"));
            card.setBalans(result.getInt("saldo"));
            card.setReiziger(reiziger);

            cards.add(card);
        }

        DAO.closeConnection();

        return cards;
    }

    public static ArrayList<OvChipkaart> findByProduct(Product product) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();
        ArrayList<OvChipkaart> cards = new ArrayList<>();

        int id = product.getProductNummer();

        PreparedStatement pstmt = conn.prepareStatement("SELECT kaartnummer FROM OV_CHIPKAART_PRODUCT WHERE productnummer = ?");
        pstmt.setInt(1, id);
        ResultSet result = pstmt.executeQuery();

        PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM OV_CHIPKAART WHERE kaartnummer = ?");
        pstmt.setInt(1, result.getInt("kaartnummer"));
        ResultSet result2 = pstmt2.executeQuery();

        while (result.next()) {
            OvChipkaart card = new OvChipkaart();
            card.setKaartNummer(result.getInt("kaartnummer"));
            card.setGeldigTot(result.getDate("geldigtot"));
            card.setKlasse(result.getInt("klasse"));
            card.setBalans(result.getInt("saldo"));
            card.setProducts(ProductOracleDAOImpl.findByOvChipkaart(card));
//            card.setReiziger(reiziger);

            cards.add(card);
        }

        DAO.closeConnection();

        return cards;
    }
}

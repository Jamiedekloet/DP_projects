package DAOP3;

import java.sql.*;
import java.util.ArrayList;

public class OvChipkaartDAOImpl implements OvChipkaartDAO {
    ArrayList<Product> products;
    private ProductOracleDAOImpl productDao;

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
//            card.setProducts(ProductOracleDAOImpl.findByOvChipkaart(card));

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
            card.setReiziger(ReizigerOracleDAOImpl.findById(result.getInt("reizigerid")));
            card.setProducts(ProductOracleDAOImpl.findByOvChipkaart(card));
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
            card.setProducts(ProductOracleDAOImpl.findByOvChipkaart(card));

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
            card.setReiziger(ReizigerOracleDAOImpl.findById(result.getInt("reizigerid")));

            cards.add(card);
        }

        DAO.closeConnection();

        return cards;
    }

    public static OvChipkaart findById(int ovChipkaarId) throws SQLException {
        OvChipkaart card = null;
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM OV_CHIPKAART WHERE kaartnummer = ?"
            );
            preparedStatement.setInt(1, ovChipkaarId);
            ResultSet result = preparedStatement.executeQuery();

            String voorletters, tussenvoegsel, achternaam;
            Date date;
            while (result.next()) {
                card = new OvChipkaart();
                card.setKaartNummer(result.getInt("kaartnummer"));
                card.setGeldigTot(result.getDate("geldigtot"));
                card.setKlasse(result.getInt("klasse"));
                card.setBalans(result.getInt("saldo"));
                card.setProducts(ProductOracleDAOImpl.findByOvChipkaart(card));
                card.setReiziger(ReizigerOracleDAOImpl.findById(result.getInt("reizigerid")));

            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return card;
    }

    @Override
    public OvChipkaart save(OvChipkaart ovChipkaart) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        ovChipkaart.getProducts().forEach(product -> {
            try {
                productDao.save(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        String strQuery = "INSERT INTO ov_chipkaart (productnummer, geldigtot, klasse, saldo, reizigerid) VALUES (?, ?, ?, ?, ?) ";
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setInt(1, ovChipkaart.getKaartNummer());
//        pstmt.setDate(2, ovChipkaart.getGeldigTot());
        pstmt.setInt(3, ovChipkaart.getKlasse());
        pstmt.setDouble(4, ovChipkaart.getBalans());
        pstmt.setInt(5, ovChipkaart.getReiziger().getId());

        pstmt.executeQuery();
        conn.close();

        return ovChipkaart;
    }

    @Override
    public OvChipkaart update(OvChipkaart ovChipkaart) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        ovChipkaart.getProducts().forEach(product -> {
            try {
                productDao.update(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        String strQuery = "UPDATE ov_chipkaart SET (productnummer, geldigtot, klasse, saldo, reizigerid) VALUES (?, ?, ?, ?, ?) WHERE productnummer="+ovChipkaart.getKaartNummer();
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setInt(1, ovChipkaart.getKaartNummer());
//        pstmt.setDate(2, ovChipkaart.getGeldigTot());
        pstmt.setInt(3, ovChipkaart.getKlasse());
        pstmt.setDouble(4, ovChipkaart.getBalans());
        pstmt.setInt(5, ovChipkaart.getReiziger().getId());

        pstmt.executeQuery();
        conn.close();

        return ovChipkaart;
    }

    @Override
    public boolean delete(OvChipkaart ovChipkaart) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

            ovChipkaart.getProducts().forEach(product -> {
                try {
                    productDao.delete(product);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        String strQuery = "DELETE FROM ov_chipkaart WHERE kaartnummer= ?";
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setInt(1, ovChipkaart.getKaartNummer());

        pstmt.executeQuery();
        conn.close();

        return true;
    }
}

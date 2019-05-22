package DAOP3;

import java.sql.*;
import java.util.ArrayList;

public class ProductOracleDAOImpl implements ProductDAO {
//    public ProductOracleDAOImpl() {}

    @Override
    public ArrayList<Product> findAll() throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();
        ArrayList<Product> products = new ArrayList<>();

        Statement allReizigers = conn.createStatement();
        ResultSet result = allReizigers.executeQuery("SELECT * FROM PRODUCT");

        while (result.next()) {
            Product product = new Product();
            product.setProductNummer(result.getInt("productnummer"));
            product.setProductNaam(result.getString("productnaam"));
            product.setBeschrijving(result.getString("beschrijving"));
            product.setPrijs(result.getDouble("prijs"));
//            product.setReiziger(ReizigerOracleDAOImpl.findById(result.getInt("reizigerid")));

            products.add(product);
        }

        DAO.closeConnection();

        return products;
    }

    @Override
    public ArrayList<Product> findByReiziger(Reiziger reiziger) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();
        ArrayList<Product> products = new ArrayList<>();

        int id = reiziger.getId();

        PreparedStatement pstmt = conn.prepareStatement("SELECT kaartnummer FROM OV_CHIPKAART WHERE reizigerid = ?");
        pstmt.setInt(1, id);
        ResultSet result = pstmt.executeQuery();

        PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM OV_CHIPKAART_PRODUCT WHERE kaartnummer = ?");
        pstmt.setInt(1, result.getInt("kaartnummer"));
        ResultSet result2 = pstmt2.executeQuery();

        PreparedStatement pstmt3 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE productnummer = ?");
        pstmt.setInt(1, result2.getInt("productnummer"));
        ResultSet result3 = pstmt3.executeQuery();

        while (result3.next()) {
            Product product = new Product();
            product.setProductNummer(result3.getInt("productnummer"));
            product.setProductNaam(result3.getString("productnaam"));
            product.setBeschrijving(result3.getString("beschrijving"));
            product.setPrijs(result3.getDouble("prijs"));
//            product.setReiziger(reiziger);

            products.add(product);
        }

        DAO.closeConnection();

        return products;
    }

    public static ArrayList<Product> findByOvChipkaart(OvChipkaart ovChipkaart) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();
        ArrayList<Product> products = new ArrayList<>();

        int id = ovChipkaart.getKaartNummer();

        PreparedStatement pstmt = conn.prepareStatement("SELECT productnummer FROM OV_CHIPKAART_PRODUCT WHERE kaartnummer = ?");
        pstmt.setInt(1, id);
        ResultSet result = pstmt.executeQuery();

        PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM PRODUCT WHERE productnummer = ?");
        pstmt.setInt(1, result.getInt("productnummer"));
        ResultSet result2 = pstmt2.executeQuery();

        while (result2.next()) {
            Product product = new Product();
            product.setProductNummer(result2.getInt("productnummer"));
            product.setProductNaam(result2.getString("productnaam"));
            product.setBeschrijving(result2.getString("beschrijving"));
            product.setPrijs(result2.getDouble("prijs"));
            product.setOVChipkaarten(OvChipkaartDAOImpl.findByProduct(product));
//            product.setReiziger(reiziger);

            products.add(product);
        }

        DAO.closeConnection();

        return products;
    }

    @Override
    public Product save() throws SQLException {
        return null;
    }

    @Override
    public Product update() throws SQLException {
        return null;
    }

    @Override
    public boolean delete() throws SQLException {
        return false;
    }
}

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
            product.setOVChipkaarten(OvChipkaartDAOImpl.findByProduct(product));
            product.setReiziger(ReizigerOracleDAOImpl.findByProduct(product));

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
            product.setOVChipkaarten(OvChipkaartDAOImpl.findByReiziger(reiziger));
            product.setReiziger(ReizigerOracleDAOImpl.findById(reiziger.getId()));

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
            product.setReiziger(ReizigerOracleDAOImpl.findByOvChipkaart(ovChipkaart));

            products.add(product);
        }

        DAO.closeConnection();

        return products;
    }

    @Override
    public Product save(Product product) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        String strQuery = "INSERT INTO product (productnummer, productnaam, beschrijving, prijs) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setInt(1, product.getProductNummer());
        pstmt.setString(2, product.getProductNaam());
        pstmt.setString(3, product.getBeschrijving());
        pstmt.setDouble(4, product.getPrijs());

        pstmt.executeQuery();
        conn.close();

        return product;
    }

    @Override
    public Product update(Product product) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        String strQuery = "UPDATE product SET (productnummer, productnaam, beschrijving, prijs) VALUES (?, ?, ?, ?) WHERE productnummer="+product.getProductNummer();
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setInt(1, product.getProductNummer());
        pstmt.setString(2, product.getProductNaam());
        pstmt.setString(3, product.getBeschrijving());
        pstmt.setDouble(4, product.getPrijs());

        pstmt.executeQuery();
        conn.close();

        return product;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        OracleBaseDao DAO = new OracleBaseDao();
        Connection conn = DAO.getConnection();

        String strQuery = "DELETE FROM product WHERE productnummer= ?";
        PreparedStatement pstmt = conn.prepareStatement(strQuery);
        pstmt.setInt(1, product.getProductNummer());

        pstmt.executeQuery();
        conn.close();

        return true;
    }
}

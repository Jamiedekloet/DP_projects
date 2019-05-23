package DAOP3;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {
    public  ArrayList<Product> findAll() throws SQLException;

    public ArrayList<Product> findByReiziger(Reiziger reiziger) throws SQLException;

//    public ArrayList<Product> findByOvChipkaart(OvChipkaart ovChipkaart) throws SQLException;

    public Product save(Product product) throws SQLException;

    public Product update(Product product) throws SQLException;

    public boolean delete(Product product) throws SQLException;
}

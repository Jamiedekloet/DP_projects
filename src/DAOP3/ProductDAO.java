package DAOP3;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {
    public ArrayList<Product> findAll() throws SQLException;

    public Product findByReiziger() throws SQLException;

    public ArrayList<OvChipkaart> findByOvChipkaart() throws SQLException;

    public Product save() throws SQLException;

    public Product update() throws SQLException;

    public boolean delete() throws SQLException;
}

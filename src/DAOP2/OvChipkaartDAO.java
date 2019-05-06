package DAOP2;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OvChipkaartDAO {
    public OvChipkaart getOvChipkaartById(int id) throws SQLException;

    public ArrayList<OvChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
}
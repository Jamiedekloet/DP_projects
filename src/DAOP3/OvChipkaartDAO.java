package DAOP3;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OvChipkaartDAO {
    public OvChipkaart getOvChipkaartById(int id) throws SQLException;

//    public ArrayList<OvChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;

    public OvChipkaart save(OvChipkaart ovChipkaart) throws SQLException;

    public OvChipkaart update(OvChipkaart ovChipkaart) throws SQLException;

    public boolean delete(OvChipkaart ovChipkaart) throws SQLException;
}
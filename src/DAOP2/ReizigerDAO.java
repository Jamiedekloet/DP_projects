package DAOP2;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface ReizigerDAO {
    public ArrayList<Reiziger> findAll() throws SQLException;

    public ArrayList<Reiziger> findByDatum(String GBdatum) throws ParseException, SQLException;

    public Reiziger save(Reiziger reiziger) throws SQLException;

    public Reiziger update(Reiziger reiziger) throws SQLException;

    public boolean delete(Reiziger reiziger) throws SQLException;




//    public closeConnection();
}

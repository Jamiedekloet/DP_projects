package DAOP2;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface ReizigerDAO {
    public ArrayList<Reiziger> findAll() throws SQLException;

    public ArrayList<Reiziger> findByDatum(String GBdatum) throws ParseException;

    public Reiziger save(Reiziger reiziger);

    public Reiziger update(Reiziger reiziger);

    public boolean delete(Reiziger reiziger);




//    public closeConnection();
}

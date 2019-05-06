package DAOP1;

import java.text.ParseException;
import java.util.List;

public interface ReizigerDAO {
    public List<Reiziger> findAll();

    public List<Reiziger> findByDatum(String GBdatum) throws ParseException;

    public Reiziger save(Reiziger reiziger);

    public Reiziger update(Reiziger reiziger);

    public boolean delete(Reiziger reiziger);




//    public closeConnection();
}

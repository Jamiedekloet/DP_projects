package DAO_simulatie;

import java.util.List;

public interface ReizigerDAO {
    public List<Reiziger> findAll();

    public List<Reiziger> findByDatum(String GBdatum);

    public Reiziger save(Reiziger reiziger);

    public Reiziger update(Reiziger reiziger);

    public Boolean delete(Reiziger reiziger);

//    public closeConnection();
}

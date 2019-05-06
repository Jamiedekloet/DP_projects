package DAOP1;

import DAOP2.Reiziger;

import java.util.Date;
import java.util.List;

public interface ReizigerDAO {
    public List<DAOP2.Reiziger> findAll();

    public List<DAOP2.Reiziger> findByDatum(Date GBdatum);

    public DAOP2.Reiziger save(DAOP2.Reiziger reiziger);

    public DAOP2.Reiziger update(DAOP2.Reiziger reiziger);

    public boolean delete(Reiziger reiziger);




//    public closeConnection();
}

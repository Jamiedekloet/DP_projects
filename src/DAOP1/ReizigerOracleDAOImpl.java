package DAOP1;

import DAOP2.Reiziger;
import DAOP2.ReizigerDAO;

import java.util.ArrayList;
import java.util.Date;

public abstract class ReizigerOracleDAOImpl implements ReizigerDAO {
    private ArrayList<Reiziger> reizigers = new ArrayList<>();

    public ArrayList<Reiziger> findAll() {
        return reizigers;
    }

    public ArrayList<Reiziger> findByDatum(Date GBdatum) {
        ArrayList<Reiziger> newReizigers = new ArrayList<>();
        for (DAOP2.Reiziger reiziger : reizigers) {
            if (reiziger.getGBdatum().compareTo(GBdatum) == 0) {
                newReizigers.add(reiziger);
            }
        }
        return newReizigers;

    }

    public DAOP2.Reiziger save(Reiziger reiziger) {
        reizigers.add(reiziger);
        return reiziger;
    }

    public DAOP2.Reiziger update(Reiziger reiziger) {
        int index = -1;

        for (int i = 0; i < reizigers.size(); i++) {
            DAOP2.Reiziger newReiziger = reizigers.get(i);

            if (newReiziger.getId() == reiziger.getId()) {
                index = i;
                break;
            }
        }

        if (index == -1)
            return null;

        reizigers.set(index, reiziger);

        return reiziger;
    }

    public boolean delete(Reiziger reiziger) {
        if (reizigers.contains(reiziger)) {
            reizigers.remove(reiziger);
            return true;
        } else {
            return false;
        }
    }
}

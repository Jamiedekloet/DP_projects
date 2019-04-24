package DAO_simulatie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReizigerOracleDAOImpl implements ReizigerDAO {
    private ArrayList<Reiziger> reizigers = new ArrayList<>();

    public ArrayList<Reiziger> findAll() {
        return reizigers;
    }

    public ArrayList<Reiziger> findByDatum(Date GBdatum) {
        ArrayList<Reiziger> newReizigers = new ArrayList<>();
        for (Reiziger reiziger : reizigers) {
            if (reiziger.getGBdatum().compareTo(GBdatum) == 0) {
                newReizigers.add(reiziger);
            }
        }
        return newReizigers;

    }

    public Reiziger save(Reiziger reiziger) {
        reizigers.add(reiziger);
        return reiziger;
    }

    public Reiziger update(Reiziger reiziger) {
        int index = -1;

        for (int i = 0; i < reizigers.size(); i++) {
            Reiziger newReiziger = reizigers.get(i);

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

    public Boolean delete(Reiziger reiziger) {
        if (reizigers.contains(reiziger)) {
            reizigers.remove(reiziger);
            return true;
        } else {
            return false;
        }

    }
}

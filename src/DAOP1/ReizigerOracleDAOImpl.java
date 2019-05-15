package DAOP1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReizigerOracleDAOImpl implements ReizigerDAO {
    private ArrayList<Reiziger> reizigers = new ArrayList<>();

    public ArrayList<Reiziger> findAll() {
        return reizigers;
    }

    public ArrayList<Reiziger> findByDatum(String GBdatum) throws ParseException {
        ArrayList<Reiziger> newReizigers = new ArrayList<>();
        Date gbdate = new SimpleDateFormat("yyyy/MM/dd").parse(GBdatum);
        for (Reiziger reiziger : reizigers) {
            if (reiziger.getGBdatum().compareTo(gbdate) == 0) {
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
                newReiziger.setNaam(reiziger.getNaam());
                newReiziger.setGBdatum(reiziger.getGBdatum());

                reizigers.set(index, newReiziger);
                break;
            }
        }

        if (index == -1) {
            return null;
        }

        return reiziger;
    }

    public boolean delete(Reiziger reiziger) {
        //System.out.println(reizigers); //Reizers is filled, for testing
        if (reizigers.contains(reiziger)) {
            reizigers.remove(reiziger);
            //System.out.println(reizigers); //And now its empty, for testing
            return true;
        } else {
            return false;
        }
    }
}

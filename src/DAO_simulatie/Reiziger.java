package DAO_simulatie;

import java.util.Date;

public class Reiziger {
    private String naam;
    private Date gbdatum;

    public Reiziger() {

    }

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String nm) {
        this.naam = nm;
    }

    public Date getGBdatum() {
        return this.gbdatum;
    }

    public void setGBdatum(Date datum) {
        this.gbdatum = datum;
    }

}

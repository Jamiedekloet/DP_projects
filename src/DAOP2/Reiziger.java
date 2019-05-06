package DAOP2;

import java.util.Date;

public class Reiziger {
    private int id;
    private String naam;
    private Date gbdatum;

    public Reiziger(int id, String nm, Date gbDate) {
        this.id = id;
        this.naam = nm;
        this.gbdatum = gbDate;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Reiziger{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", gbdatum=" + gbdatum +
                '}';
    }
}

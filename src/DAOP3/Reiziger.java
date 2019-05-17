package DAOP3;

import java.util.ArrayList;
import java.util.Date;

public class Reiziger {
    private int id;
    private String naam;
    private Date gbdatum;
    private ArrayList<OvChipkaart> cards;

    public Reiziger(int id, String nm, Date gbDate, ArrayList<OvChipkaart> cards) {
        this.id = id;
        this.naam = nm;
        this.gbdatum = gbDate;
        this.cards = cards;
    }

    public Reiziger() {}

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id;}

    public String getNaam() {
        return this.naam;
    }

    public String getVoorletters() {
        String[] naam = this.getNaam().split(" ");

        if (naam.length == 3) {
            return naam[0];
        } else {
            return null;
        }
    }

    public String getMiddleName() {
        String[] naam = this.getNaam().split(" ");

        if (naam.length == 3) {
            return naam[1];
        } else {
            return null;
        }
    }

    public String getLastName() {
        String[] naam = this.getNaam().split(" ");

        if (naam.length == 3) {
            return naam[2];
        } else {
            return null;
        }
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

    public ArrayList<OvChipkaart> getCards() {
        return cards;
    }

    public void setCards(ArrayList<OvChipkaart> cards) {
        this.cards = cards;
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

package DAOP3;

import java.util.Date;

public class OvChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double balans;
    private Reiziger reiziger;

    public OvChipkaart() {}

    public OvChipkaart(int kaartNummer, Date geldigTot, int klasse, float saldo, Reiziger reiziger) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.balans = saldo;
        this.reiziger = reiziger;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public double getBalans() {
        return balans;
    }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public void setBalans(double balans) {
        this.balans = balans;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "OvChipkaart{" +
                "kaartNummer=" + kaartNummer +
                ", geldigTot=" + geldigTot +
                ", klasse=" + klasse +
                ", balans=" + balans +
                '}';
    }
}
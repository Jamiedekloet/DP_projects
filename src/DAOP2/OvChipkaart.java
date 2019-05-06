package DAOP2;

import java.util.Date;

public class OvChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double balans;
//    private


    public OvChipkaart() {}

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
}
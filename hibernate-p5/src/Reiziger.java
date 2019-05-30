import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name="REIZIGER")
public class Reiziger {
    @Id
    @Column(name = "REIZIGERID", unique = true, nullable = false)
    private int id;

    @Column(name ="VOORLETTERS", length = 10)
    private String voorLetters;

    @Column(name = "TUSSENVOEGSEL", length = 10)
    private String tussenvoegsel;

    @Column(name = "ACHTERNAAM")
    private String achterNaam;

    @Column(name = "GEBOORTEDATUM")
    private Date gbdatum;

    @OneToMany(targetEntity = OvChipkaart.class, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "reiziger")
    private ArrayList<OvChipkaart> cards;

    public Reiziger(int id, String vl, String tv, String an, Date gbDate, ArrayList<OvChipkaart> cards) {
        this.id = id;
        this.voorLetters = vl;
        this.tussenvoegsel = tv;
        this.achterNaam = an;
        this.gbdatum = gbDate;
        this.cards = cards;
    }

    public Reiziger() {}

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id;}

//    public String getNaam() {
//        return this.naam;
//    }

    public String getVoorletters() {
//        String[] naam = this.getNaam().split(" ");
//
//        if (naam.length == 3) {
//            return naam[0];
//        } else {
//            return null;
//        }
        return this.voorLetters;
    }

    public String getMiddleName() {
//        String[] naam = this.getNaam().split(" ");
//
//        if (naam.length == 3) {
//            return naam[1];
//        } else {
//            return null;
//        }
        return this.tussenvoegsel;
    }

    public String getLastName() {
//        String[] naam = this.getNaam().split(" ");
//
//        if (naam.length == 3) {
//            return naam[2];
//        } else {
//            return null;
//        }
        return this.achterNaam;
    }

//    public void setNaam(String nm) {
//        this.naam = nm;
//    }

    public void setVoorLetters(String voorLetters) {
        this.voorLetters = voorLetters;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
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
}

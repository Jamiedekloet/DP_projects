package DAOP3;

import java.util.ArrayList;

public class Product {
    private int productNummer;
    private String productNaam;
    private String beschrijving;
    private double prijs;
    private ArrayList<OvChipkaart> cards;
    private Reiziger reiziger;

    public Product() {}

    public Product(int productNummer, String productNaam, String beschrijving, double prijs) {
        this.productNummer = productNummer;
        this.productNaam = productNaam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getProductNaam() {
        return productNaam;
    }

    public void setProductNaam(String productNaam) {
        this.productNaam = productNaam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public void setOVChipkaarten(ArrayList<OvChipkaart> cards) {
        this.cards = cards;
    }

    public ArrayList<OvChipkaart> getCards() {
        return cards;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productNummer != product.productNummer) return false;
        if (Double.compare(product.prijs, prijs) != 0) return false;
        if (productNaam != null ? !productNaam.equals(product.productNaam) : product.productNaam != null) return false;
        return beschrijving != null ? beschrijving.equals(product.beschrijving) : product.beschrijving == null;

    }

    @Override
    public String toString() {
        return "Product{" +
                "productNummer=" + productNummer +
                ", productNaam='" + productNaam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                '}';
    }
}

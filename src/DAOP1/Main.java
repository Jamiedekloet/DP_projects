package DAOP1;


import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        Reiziger reiziger = new Reiziger(1,"Piet", java.sql.Date.valueOf("1990-12-23"));
        System.out.println(reiziger.getNaam());

        ReizigerOracleDAOImpl reizigerDAO = new ReizigerOracleDAOImpl();
        reizigerDAO.save(reiziger);
        reizigerDAO.findAll().forEach(System.out::println);

        reiziger.setNaam("Jan");
        reizigerDAO.update(reiziger);

        reizigerDAO.findAll().forEach(System.out::println);

        try {
            System.out.println("\nFindbyDate:");
            reizigerDAO.findByDatum("1990/12/23").forEach(System.out::println);
        } catch (ParseException e) {
            System.out.println(e);
        }

        System.out.println("\nDelete:");
        if (reizigerDAO.delete(reiziger)) {
            System.out.println("Reiziger verwijderd!");
        }
    }
}

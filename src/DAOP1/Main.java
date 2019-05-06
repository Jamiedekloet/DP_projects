//package DAOP1;
//
//import DAOP2.Reiziger;
//import DAOP2.ReizigerOracleDAOImpl;
//
//public class Main {
//    public static void main(String[] args) {
//        DAOP2.Reiziger reiziger = new Reiziger(1,"Piet", java.sql.Date.valueOf("1990-12-23"));
//        System.out.println(reiziger.getNaam());
//
//        DAOP2.ReizigerOracleDAOImpl reizigerDAO = new ReizigerOracleDAOImpl();
//        reizigerDAO.save(reiziger);
//        reizigerDAO.findAll().forEach(System.out::println);
//
//        reiziger.setNaam("Jan");
//        reizigerDAO.update(reiziger);
//
//        reizigerDAO.findAll().forEach(System.out::println);
//    }
//}

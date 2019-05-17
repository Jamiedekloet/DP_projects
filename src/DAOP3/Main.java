package DAOP3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        ArrayList<OvChipkaart> cards = null;
        Reiziger reiziger = new Reiziger(1,"Piet", java.sql.Date.valueOf("1990-12-23"), cards

        );
        System.out.println(reiziger.getNaam());

        ReizigerOracleDAOImpl reizigerDAO = new ReizigerOracleDAOImpl();
        reizigerDAO.save(reiziger);
        reizigerDAO.findAll().forEach(System.out::println);

        reiziger.setNaam("Jan");
        reizigerDAO.update(reiziger);

        reizigerDAO.findAll().forEach(System.out::println);

        OracleBaseDao dbCon = new OracleBaseDao();
        Connection conn = dbCon.getConnection();
        Statement insert = conn.createStatement();
        int id = reiziger.getId();
        insert.executeQuery("INSERT INTO OV_CHIPKAART (KAARTNUMMER, GELDIGTOT, KLASSE, SALDO, REIZIGERID) VALUES (923337402, '15-01-19', 2, 40, "+id+")");


        OvChipkaartDAOImpl kaartDAO = new OvChipkaartDAOImpl();
        ArrayList<OvChipkaart> test = kaartDAO.findByReiziger(reiziger);

        for (OvChipkaart kaart : test) {
            System.out.println(kaart.getKaartNummer()+"\n");
        }
    }
}

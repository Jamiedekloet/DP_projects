import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {
//  private static SessionFactory factory;
  public static void main(String[] args) throws SQLException, ParseException {

      ArrayList<OvChipkaart> cardsFormReizigerOne = new ArrayList<>();

      Reiziger reiziger1 = new Reiziger(1, "J.L.", "de", "Kloet", java.sql.Date.valueOf("1999-06-26"), null);
      OvChipkaart card1 = new OvChipkaart(1234, java.sql.Date.valueOf("2020-01-01"), 1, 20, reiziger1);
      cardsFormReizigerOne.add(card1);
      reiziger1.setCards(cardsFormReizigerOne);

      ReizigerDAO reizigerdao = new ReizigerDAO();
      reizigerdao.createReiziger(reiziger1);

      OvChipkaartDAO carddao = new OvChipkaartDAO();
      carddao.createCard(card1);

      System.out.println("De nieuwe reiziger:");
      reizigerdao.findAll();

      System.out.println("\n de nieuwe kaart:");
      carddao.findAll();

      System.out.println("\n Geen kaarten meer:");
      carddao.deleteCard(card1);
      carddao.findAll();

  }
}

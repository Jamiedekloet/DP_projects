//package test;
//
//// Vergeet deze import niet
//import java.sql.*;
//
//public class Main {
//    //Zorg ter voorbereiding dat je ojdbc.jar download en toevoegt aan je project.
//
//    //Aanmaken van de variabelen die je connectie specificeren. In dit geval een gebruiker "harry" met password "harry"
//    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
//    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:49161/xe";
//    private static final String DB_USER = "JAMIE";
//    private static final String DB_PASS = "password";
//    private static Connection conn;
//
//    // De methode die met JDBC aan de slag gaat moet een SQLException opvangen of gooien
//    public static void main(String[] args) throws SQLException{
//        //Besluit welke driver je gaat gebruiken voor je verbinding
//        try {
//            Class.forName(DB_DRIV).newInstance();
//        }
//        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
//            e1.printStackTrace();
//        }
//
//        // Leg de connectie met de database
//        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
////        System.out.println("Connection made");
//
//        Statement stmt = conn.createStatement();
//        String queryText = "SELECT ANR, NAAM FROM AFDELINGEN";
//        ResultSet rs = stmt.executeQuery(queryText);
//
//        while (rs.next()) {
//            System.out.println(rs.getInt("ANR") + ", " + rs.getString("NAAM"));
//        }
//        rs.close();
//        stmt.close();
//
//
//        Statement stmt2 = conn.createStatement();
//        String queryText2 = "INSERT INTO AFDELINGEN (ANR, NAAM, LOCATIE, HOOFD) VALUES (50, 'TESTAFDELING', 'UTRECHT', 7566)";
//        ResultSet rs2 = stmt2.executeQuery(queryText2);
//        rs2.close();
//        stmt2.close();
//
//
//        Statement stmt3 = conn.createStatement();
//        String queryText3 = "DELETE FROM AFDELINGEN WHERE ANR=50";
//        ResultSet rs3 = stmt3.executeQuery(queryText3);
//        rs3.close();
//        stmt3.close();
//
//
////        Prepared statements
//
//        String strQuery = "INSERT INTO AFDELINGEN ? VALUES ?";
//        PreparedStatement pstmt = conn.prepareStatement(strQuery);
//        pstmt.setString(2, "(50, 'TESTAFDELING', 'UTRECHT', 7566)");
//        pstmt.setString(1, "(ANR, NAAM, LOCATIE, HOOFD)");
//
//        String strQuery2 = "DELETE FROM AFDELINGEN WHERE anr = ? AND locatie = ?";
//        PreparedStatement pstmt2 = conn.prepareStatement(strQuery2);
//        pstmt2.setInt(1, 60);
//        pstmt2.setString(2, "BAARN");
//
//
//        Statement stmt4 = conn.createStatement();
//        String queryText4 = "SELECT ANR, NAAM FROM AFDELINGEN";
//        ResultSet rs4 = stmt4.executeQuery(queryText4);
//        System.out.println("\n");
//
//        while (rs4.next()) {
//            System.out.println(rs4.getInt("ANR") + ", " + rs4.getString("NAAM"));
//        }
//        rs4.close();
//        stmt4.close();
//
//        // De resultset, het statement en de verbinding sluiten
//        conn.close();
//
//    }
//}
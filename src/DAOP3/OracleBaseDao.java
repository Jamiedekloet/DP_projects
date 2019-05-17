package DAOP3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleBaseDao {

        private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
        private static final String DB_URL = "jdbc:oracle:thin:@//localhost:49161/xe";
        private static final String DB_USER = "V1DP_OV";
        private static final String DB_PASS = "password";
        private static Connection conn;

        protected Connection getConnection() throws SQLException {
            try {
                Class.forName(DB_DRIV).newInstance();
            }
            catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//            System.out.println("Connection made");
            return conn;


        }

        public boolean closeConnection() {
            try {
                conn.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
        }
    }
}

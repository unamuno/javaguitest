package guitest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Postgress {
    
    private String url = "jdbc:postgresql://localhost/testdb3";
    private String user = "postgres";
    private String password = "googlemail";
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private ResultSet CountCollums = null;
    private ResultSet CountRows = null;
    private String[][] varResults;
    private int varColumAnzahl=0;
    private int varRowAnzahl=0;

    public boolean ConnectionTest() {

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
                return true;
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Postgress.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Postgress.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return false;
    }
    
    public String[][] getBooks() {

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            
            //Abfrage der Anzahl Spalten in der Tabelle
            CountCollums = st.executeQuery("select count(*) from information_schema.columns where table_name='books'");
            if(CountCollums.next()) {
                varColumAnzahl = CountCollums.getInt(1);
                System.out.println("Anzahl Spalten: "+String.valueOf(varColumAnzahl));
            }
            //Abfrage der Anzahl Zeilen in der Tabelle
            st = con.createStatement();
            CountRows = st.executeQuery("select count(*) from books");
            if(CountRows.next()) {
                varRowAnzahl = CountRows.getInt(1);
                System.out.println("Anzahl zeilen: "+String.valueOf(varRowAnzahl));
            }
            
           
            varResults= new String[varColumAnzahl][varRowAnzahl];
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM books");
            int i=0;
            while (rs.next()) {
                for (int j = 0; j < varColumAnzahl; j++) {
                    varResults[i][j]=(rs.getString(j+1));
                    System.out.print("  Zeile"+i+": "+rs.getString(j+1));
                }
                System.out.println("");
                
                i++;
            }
            return varResults;

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Postgress.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Postgress.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return varResults=new String[1][1];
    }
}
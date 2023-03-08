package fr.eni.projet.trocechere;
import java.sql.*;

public class Test {

	 
		public static void main(String[] args) {
	 
	           Connection con = null;
	           String conUrl = "jdbc:sqlserver://localhost; databaseName=TROC_ENCHERES; user=sa; password=P@ssword;";
	 
		   try {
	            // ...
		    con = DriverManager.getConnection(conUrl);
	            // ... 
	   	  } catch (Exception e) { e.printStackTrace(); }
	             finally {
	               if (con != null) try { con.close(); } catch(Exception e) {}
	             }
		}
	   }

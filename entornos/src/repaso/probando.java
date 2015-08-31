package repaso;
import java.sql.*;
import java.text.*;

import javax.swing.JOptionPane;
public class probando {
	
	public probando(){};
	
		Connection db = null;
		private String url = "jdbc:mysql://localhost:3306/regantes";
		private String usu = "root";
		private String pwd = "";
		private Statement stm;
		private ResultSet rs;
		
		public Connection conexion(){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				db = DriverManager.getConnection(url,usu,pwd);
				JOptionPane.showMessageDialog(null, "Bien hecho destroyer");
			}catch(ClassNotFoundException | SQLException e){
				JOptionPane.showMessageDialog(null, "Error: " + e);
			}
			return db;
		}
		
		public void consultar() throws SQLException
		{
		rs = stm.executeQuery("SELECT * FROM regantes");
		while(rs.next())
		{
		System.out.println(rs.getString("nombre"));
		System.out.println(rs.getString("dni"));
		}
		}

	public static void main(String[]args) throws SQLException{
		probando pru = new probando();
		pru.conexion();
		String nombre = "Mariano";
		pru.consultar();
	}
}



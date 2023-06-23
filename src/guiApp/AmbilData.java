package guiApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AmbilData {
	static void login(String inputUsername,String inputPass) {
		Connection connection = null;

		int jumlahData = 0;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/apotek","root", "");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user");
			while(rs.next()) {
				jumlahData = jumlahData + 1;
			}
		} catch (SQLException ex) {
			System.out.println("Gagal Membuat koneksi.");
			ex.printStackTrace();
		}
		
		if(connection != null){
			System.out.println("Selamat, Anda telah berhasil membuat koneksi ke database MySQL");
		}else{
			System.out.println("Maaf, Anda gagal membuat koneksi");
		}
		
		String[] arrName = new String[jumlahData];
		String[] arrPass = new String[jumlahData];
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user");
			int index = 0;
			while(rs.next()) {
				arrName[index] = rs.getString("Username");
				arrPass[index] = rs.getString("Pass");
				index = index + 1;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			connection.close();
		} catch (SQLException ex) {
			System.out.println("Gagal untuk membuat koneksi");
			ex.printStackTrace();
		}
		try {
//			System.out.println("jumlahData : "+jumlahData);
			for(int i = 0;i<jumlahData;i++) {
				if(inputUsername.equals(arrName[i]) &&inputPass.equals(arrPass[i]) ) {
					out();
				}
//				System.out.print(arrName[i]+":");System.out.print(arrPass[i]);System.out.println();
			}
		}catch(Exception e){}
		
	}
	static void out() {
		System.out.println("berhasil login");
	}
	public static void main(String[] args){
		System.out.println("======= Koneksi JDBC dengan MySQL ======= ");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			login("User1" ,"1");
			
			} catch (ClassNotFoundException ex) {
			System.out.println("MySQL JDBC Driver tidak ditemukan");
			ex.printStackTrace();
		}
		System.out.println("MySQL JDBC Driver berhasil didaftarkan");
		
	}
}

package ftpk1;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import com.oracle.*;

public class Testoracle2 {
	public static void main(String[] args) {
		Connection con = null;
		//Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt=null,pstmt1=null;
		try {
			System.out.println("hi");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			//stmt = con.createStatement();
			//String ab="System";
			String query1="select fileno,forby,forto from trans where forto is NULL ";
			//String query2="select * from master";
			//String query="Update master set pass=? where userno=700";
			//pstmt=con.prepareStatement(query);
			//pstmt.setInt(1,701);
			//pstmt.setString(1, "tmvrises");
			//pstmt.setInt(3,null);
			//pstmt.setString(3, "chair");
			System.out.println("hi2");
			pstmt1=con.prepareStatement(query1);
			//pstmt1.setString(1, null);
			//pstmt.executeUpdate();
			rs=pstmt1.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			}
			
			
//			while(rs.next())
//			{
//				//System.out.println(rs.getString("depname")+"/t/t/t"+rs.getInt(1));
//				System.out.format("%-20s %-5d",rs.getString("depname"),rs.getInt(1));
//				System.out.println();
//			}
//			pstmt1=con.prepareStatement(query2);
//			rs=pstmt1.executeQuery();
//			while(rs.next())
//			{
//				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
//			}
//			
			System.out.println("hi3");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//rs.close();
				pstmt1.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}





//rs = stmt.executeQuery("SELECT * FROM dept");
		/*	while(rs.next()) {
				System.out.print(rs.getString(1) + "\t");
				System.out.println(rs.getString(2));
			}*/

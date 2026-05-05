package crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Login {
	String url = "jdbc:mysql://localhost:3306/jdbc";
	String user = "root";
	String pass = "1234";
	Connection con;
	public Login() throws SQLException{
		con=DriverManager.getConnection(url, user, pass);
	}
	public boolean adminlog(int user, String pass) throws SQLException{
		boolean res = false;
		String q = "select * from adminlogin where user=? and pass=?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1, user);
		pst.setString(2, pass);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			res=!res;
		}
		return res;
	}
	public int add(int regno, String name, String dob, String gen, String course, int fees) throws SQLException{
		String q = "insert into student(Regno, Name, Dob, Gender, Course, Fees) values (?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1, regno);
		pst.setString(2, name);
		pst.setString(3, dob);
		pst.setString(4, gen);
		pst.setString(5, course); 
		pst.setInt(6, fees);
		int r= pst.executeUpdate();
		return r;
	}
	public void view(int regno) throws SQLException{
		String q = "select * from student where regno = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1, regno);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			System.out.println("Register Number : "+rs.getInt(1));
			System.out.println("Name : "+rs.getString(2));
			System.out.println("DOB : "+rs.getDate(3));
			System.out.println("Gender : "+rs.getString(4));
			System.out.println("Course : "+rs.getString(5));
			System.out.println("Fees : "+rs.getInt(6));
		}
	}
	public int edit(int regno, int fees) throws SQLException{
		String q = "update student set fees = ? where regno = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1, fees);
		pst.setInt(2, regno);
		int r = pst.executeUpdate();
		return r;
	}
	public int remove(int regno) throws SQLException{
		String q = "delete from student where regno = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1, regno);
		int r = pst.executeUpdate();
		return r;
	}
	public void stureg(int regno, String uid, String pass) throws SQLException{
		String q = "create table StudentLogin(regno int primary key, uid varchar(20), pass varchar(20))";
		Statement smt = con.createStatement();
		smt.execute(q);
	}
	public int stuadd(int regno, String uid, String pass) throws SQLException{
		String q= "insert into StudentLogin(regno, uid, pass) values (?,?,?)";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1, regno);
		pst.setString(2, uid);
		pst.setString(3, pass);
		int r = pst.executeUpdate();
		return r;
	}
	public boolean stuLog(int regno, String uid, String pass) throws SQLException{
		boolean res = false;
		String q = "select * from StudentLogin where regno=? and uid=? and pass=? ";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1, regno);
		pst.setString(2, uid);
		pst.setString(3, pass);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			res=!res;
		}
		return res;
	}
	public int stuEdit(int regno, String uid, String up)throws SQLException{
		String q = "update StudentLogin set uid = ? , pass = ?  where regno = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setString(1, uid);
		pst.setString(2, up);
		pst.setInt(3, regno);
		int r = pst.executeUpdate();
		return r;
	}
	public int stuDel(int regno) throws SQLException{
		String q = "delete from StudentLogin where regno = ?";
		PreparedStatement pst = con.prepareStatement(q);
		pst.setInt(1, regno);
		int r = pst.executeUpdate();
		return r;
	}
}

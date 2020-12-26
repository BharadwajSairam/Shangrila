package le.ac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserVerification {

	
	public boolean userExist(String userName) {

		String sql = "SELECT * from admin WHERE UserName=?";
		boolean userExist = false;
		try (Connection connect = ReportVerfication.getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			pstmt.setString(1, userName);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					userExist = true;
					break;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return userExist;

	}

	public User checkUser(String user, String password) {

		String sql = "SELECT * from admin WHERE UserName=? and Passwordhash=?";
		User u = null;

		try (Connection connect = ReportVerfication.getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			pstmt.setString(1, user);
			pstmt.setString(2, password);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					String uname = rs.getString("UserName");
					String pass = rs.getString("PasswordHash");
					u = new User(uname, pass);
					break;

				}
			}
		} catch (SQLException ex) {
			System.out.println("erro is here");
			ex.printStackTrace();
		}
		return u;
	}
}

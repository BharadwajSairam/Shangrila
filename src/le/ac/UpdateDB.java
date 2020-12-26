package le.ac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

public class UpdateDB {
	
	public String insertdb(HttpServletRequest req) {

		String sql = "insert into testresult(Email,Fullname,Age,Gender,Address,Postcode,TTN,TestResult) values(?,?,?,?,?,?,?,?)";

		try (Connection connect = ReportVerfication.getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			pstmt.setString(1, req.getParameter("email"));
			pstmt.setString(2, req.getParameter("name"));
			pstmt.setInt(3, Integer.parseInt(req.getParameter("age")));
			pstmt.setString(4, req.getParameter("gender"));
			pstmt.setString(5, req.getParameter("address"));
			pstmt.setString(6, req.getParameter("postcode"));
			pstmt.setString(7, req.getParameter("Tnncode"));
			pstmt.setString(8, req.getParameter("result"));
			pstmt.executeUpdate();
			pstmt.close();

			updatedb(req.getParameter("Tnncode"));
			return "";

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Insert failed";

	}

	public void updatedb(String tnn) {

		String sql = "update  hometestkit set used=1 where TNN_Code=?";

		try (Connection connect = ReportVerfication.getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			pstmt.setString(1, tnn);
			int i = pstmt.executeUpdate();
			System.out.println(i);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}

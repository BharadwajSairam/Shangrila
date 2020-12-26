package le.ac;

import java.sql.*;
import java.util.*;
import com.google.gson.Gson;

public class ReportVerfication {

	private static Connection connect = null;
	private static String host = "localhost";
	private static String database = "";
	private static String username = "";
	private static String password = "";

	public static Connection getConnection() {
		if (connect == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String conn_string = "jdbc:mysql://" + host + "/" + database;
				Connection connect = DriverManager.getConnection(conn_string, username, password);
				return connect;
			} catch (Exception ex) {
				return null;
			}
		} else {
			return connect;
		}
	}

	public String TNNUsed(String TTNcode) {

		String sql = "select * from hometestkit where used=1 and TNN_Code=?";
		try (Connection connect = getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			pstmt.setString(1, TTNcode);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					return "TNN already used";
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "";

	}

	public String Cases() {

		String sql = "SELECT TestResult, count(*) as number FROM testresult GROUP BY TestResult";
		Gson gsonObj = new Gson();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		try (Connection connect = getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Map<Object, Object> map = null;
					map = new HashMap<Object, Object>();
					map.put("label", rs.getString("TestResult"));
					map.put("y", rs.getInt("number"));
					list.add(map);
				}
				String dataPoints = gsonObj.toJson(list);
				return dataPoints;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "";

	}

	public boolean emailUsed(String email) {

		String sql = "select * from testresult where email=?";
		boolean emailExist = false;
		try (Connection connect = getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			pstmt.setString(1, email);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					emailExist = true;
					break;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return emailExist;

	}

	public String ValidTNN(String tnncode) {

		String sql = "select TNN_code from hometestkit";
		List<String> tnn = new ArrayList<String>();
		try (Connection connect = getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					tnn.add(rs.getString("TNN_code"));
				}
				if (tnn.contains(tnncode)) {
					return "";
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "Invalid TNN";

	}

}

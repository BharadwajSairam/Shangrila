package le.ac;

import java.sql.*;
import java.util.*;
import com.google.gson.Gson;

public class FetchCharts {

	
	public String Chart1() {

		String sql = "SELECT TestResult, count(*) as number FROM testresult GROUP BY TestResult";
		Gson gsonObj = new Gson();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		try (Connection connect = ReportVerfication.getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Map<Object, Object> map = null;
					map = new HashMap<Object, Object>();
					map.put("label", rs.getString("TestResult"));
					map.put("y", rs.getInt("number"));
					list.add(map);
				}
				System.out.println(list);
				String dataPoints = gsonObj.toJson(list);
				System.out.println(dataPoints);
				return dataPoints;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "";

	}

	public String Chart2() {

		String sql = "SELECT postcode, count(*) as cases FROM testresult where testresult=\"Positive\" GROUP BY postcode";
		Gson gsonObj = new Gson();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		try (Connection connect = ReportVerfication.getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Map<Object, Object> map = null;
					map = new HashMap<Object, Object>();
					map.put("label", rs.getString("postcode"));
					map.put("y", rs.getInt("cases"));
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

	public String Chart3() {

		String sql = "SELECT age, count(*) as cases FROM testresult where testresult=\"Positive\" GROUP BY age";
		Gson gsonObj = new Gson();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		try (Connection connect = ReportVerfication.getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			int count1 = 0, count2 = 0, count3 = 0, count4 = 0;
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					int age = rs.getInt("age");
					if (age > 0 && age <= 20) {
						count1 += rs.getInt("cases");
					} else if (age > 20 && age <= 40) {
						count2 += rs.getInt("cases");
					} else if (age > 40 && age <= 60) {
						count3 += rs.getInt("cases");
					} else if (age > 60 && age <= 100) {
						count4 += rs.getInt("cases");
					}
				}
				Map<Object, Object> map = null;
				map = new HashMap<Object, Object>();
				map.put("label", "Age[0-20]");
				map.put("y", count1);
				list.add(map);
				map = new HashMap<Object, Object>();
				map.put("label", "Age[21-40]");
				map.put("y", count2);
				list.add(map);
				map = new HashMap<Object, Object>();
				map.put("label", "Age[41-60]");
				map.put("y", count3);
				list.add(map);
				map = new HashMap<Object, Object>();
				map.put("label", "Age[61-100]");
				map.put("y", count4);
				list.add(map);
				String dataPoints = gsonObj.toJson(list);
				return dataPoints;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "";

	}

	public String Chart4(String ageGroup) {

		String sql = "SELECT postcode, count(*) as cases FROM testresult where testresult=\"Positive\" and age>? and age<=? GROUP BY postcode";
		Gson gsonObj = new Gson();

		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		try (Connection connect = ReportVerfication.getConnection(); PreparedStatement pstmt = connect.prepareStatement(sql);) {
			System.out.println(ageGroup);
			switch (ageGroup) {
			case "0-20":
				pstmt.setInt(1, 0);
				pstmt.setInt(2, 20);
				break;
			case "21-40":
				pstmt.setInt(1, 21);
				pstmt.setInt(2, 40);
				break;
			case "41-60":
				pstmt.setInt(1, 41);
				pstmt.setInt(2, 60);
				break;
			case ">60":
				pstmt.setInt(1, 60);
				pstmt.setInt(2, 100);
				break;
			default:
				break;
			}
			System.out.println(pstmt);
			// pstmt.setString(1,TTNcode);
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Map<Object, Object> map = null;
					map = new HashMap<Object, Object>();
					map.put("label", rs.getString("postcode"));
					map.put("y", rs.getInt("cases"));
					list.add(map);
				}

				String dataPoints = gsonObj.toJson(list);
				System.out.println(dataPoints);
				return dataPoints;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return "";
	}
}

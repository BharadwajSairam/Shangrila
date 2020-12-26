package le.ac;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReportValidation")
public class ReportValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReportValidation() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		Map<String, String[]> param = new HashMap<String, String[]>();
		param = req.getParameterMap();
		boolean emailUsed = false;
		if (param.containsKey("TNNcode") && param.size() == 1) {

			String TNNcode = req.getParameter("TNNcode");
			ReportVerfication dbOperator = new ReportVerfication();
			out.println(dbOperator.TNNUsed(TNNcode) + dbOperator.ValidTNN(TNNcode));
		} else if (param.containsKey("email") && param.size() == 1) {
			
			ReportVerfication dbOperator = new ReportVerfication();
			emailUsed = dbOperator.emailUsed(req.getParameter("email"));
			if (emailUsed) {
				out.println("email already used");
			}
		} else if (param.size() == 9) {
			UpdateDB dbOperator = new UpdateDB();
			res.sendRedirect("SELF-REPORT.html");
			out.println(dbOperator.insertdb(req));

		}
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

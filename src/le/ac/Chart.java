package le.ac;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

@WebServlet("/Chart")
public class Chart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Chart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		FetchCharts dbOperator = new FetchCharts();

		String list = "";
		if (request.getParameter("ID").equalsIgnoreCase("1")) {
			JsonObject x = new JsonObject();
			list = dbOperator.Chart1();
			x.addProperty("data", list);
			out.println(x);
		} else if (request.getParameter("ID").equalsIgnoreCase("2")) {
			JsonObject y = new JsonObject();
			list = dbOperator.Chart2();
			y.addProperty("data", list);
			out.println(y);
		} else if (request.getParameter("ID").equalsIgnoreCase("3")) {
			JsonObject z = new JsonObject();
			list = dbOperator.Chart3();
			z.addProperty("data", list);
			out.println(z);
		} else {
			JsonObject z = new JsonObject();
			list = dbOperator.Chart4(request.getParameter("ID"));
			z.addProperty("data", list);
			out.println(z);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

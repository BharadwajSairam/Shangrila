package le.ac;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin")
public class Adminlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Adminlogin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		/*
		 * Cookie[] cookies = req.getCookies();String user=""; for (int i = 0 ; i <
		 * cookies.length ; i++) { if (cookies[i].getName().equals("username")) {user =
		 * cookies[i].getValue();} } System.out.println("Previous login by: "+user);
		 * 
		 */
		Enumeration<String> variableNames = req.getParameterNames();
		int i = 0;
		UserVerification dbOperator = new UserVerification();
		while (variableNames.hasMoreElements()) {
			String paraName = (String) variableNames.nextElement();
			if (paraName.equalsIgnoreCase("password")) {
				i = 1;
			}
		}
		if (i == 0) {
			if (dbOperator.userExist(req.getParameter("username"))) {
				out.println("");
			} else {
				out.println("User does not exist");
			}
		} else {

			String name = req.getParameter("username");
			String pass = req.getParameter("password");
			HashGenerator hash = new HashGenerator();
			@SuppressWarnings("static-access")
			String passhash = hash.getMD5Hash(pass);
			System.out.println(passhash);

			User u = dbOperator.checkUser(name, passhash);
			HttpSession se = req.getSession();
			System.out.println(u);

			if (u != null) {
				se.setAttribute("User", u);
				res.sendRedirect("Dashboard.jsp");
			} else {
				res.sendRedirect("error.jsp?errorid=1");

			}

			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

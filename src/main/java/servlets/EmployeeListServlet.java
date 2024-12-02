package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import model.Employee;

@WebServlet("/EmployeeListServlet")
public class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeDao dao = new EmployeeDao();
		List<Employee> employeeList = new ArrayList<>();
		employeeList = dao.getEmployeeList();
		
		System.out.println(employeeList);
		request.setAttribute("employees", employeeList);
		
		
		//Forwards the request and response objects to index.jsp for rendering.
		//The EmployeeListServlet.jsp page can now access the "employees" and "departments" attributes to display the employee list and department options.
		  request.getRequestDispatcher("EmployeeList.jsp").forward(request, response);
	}

}

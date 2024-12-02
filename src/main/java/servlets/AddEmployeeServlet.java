package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.RoleDao;
import model.Department;
import model.Employee;
import model.Role;


@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Do Get method to load form with data
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleDao roleDAO = new RoleDao();
        DepartmentDao departmentDAO = new DepartmentDao();
        EmployeeDao employeeDAO = new EmployeeDao();

        // Retrieve the list of roles, departments, and managers
        List<Role> roles = roleDAO.getAllRoles();
        System.out.println(roles);
        List<Department> departments = departmentDAO.getAllDepartments();
        List<Employee> managers = employeeDAO.getManagersList();

        // Set these lists as request attributes
        request.setAttribute("roles", roles);
        request.setAttribute("departments", departments);
        request.setAttribute("managers", managers);

        // Forward the request to the JSP
        request.getRequestDispatcher("AddEmployee.jsp").forward(request, response);
    }

    // Do Post method for handling form submission (Employee addition)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int depid = Integer.parseInt(request.getParameter("department"));
        int roleid = Integer.parseInt(request.getParameter("role"));
        String managerParam = request.getParameter("manager");
        Integer manager = (managerParam != null && !managerParam.isEmpty()) ? Integer.parseInt(managerParam) : null;

        // Create an Employee object and set its properties
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);
        employee.setDepartmentId(depid);
        employee.setRoleId(roleid);
        employee.setReportingManager(manager);

        EmployeeDao dao = new EmployeeDao();
        dao.addEmployee(employee);

        // Redirect to the employee list page
        response.sendRedirect("EmployeeListServlet");
    }
}

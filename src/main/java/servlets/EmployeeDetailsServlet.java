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


@WebServlet("/EmployeeDetailsServlet")
public class EmployeeDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);

            // Fetch the data
            EmployeeDao employeeDAO = new EmployeeDao();
            Employee employee = employeeDAO.getEmployee(id);

            RoleDao roleDAO = new RoleDao();
            DepartmentDao departmentDAO = new DepartmentDao();

            List<Role> roles = roleDAO.getAllRoles();
            List<Department> departments = departmentDAO.getAllDepartments();
            List<Employee> managers = employeeDAO.getManagersList();
            System.out.println(managers);
            // Set attributes for JSP
            request.setAttribute("employee", employee);
            request.setAttribute("roles", roles);
            request.setAttribute("departments", departments);
            request.setAttribute("managers", managers);

            // Forward to JSP
            request.getRequestDispatcher("EmployeeDetails.jsp").forward(request, response);
        } else {
            response.sendRedirect("EmployeeListServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteParam = request.getParameter("delete");
        int id = Integer.parseInt(request.getParameter("id"));
        EmployeeDao dao = new EmployeeDao();

        if (deleteParam != null) {
            dao.deleteEmployee(id);
        } else {
   
            String name = request.getParameter("name");
            double salary = Double.parseDouble(request.getParameter("salary"));
            int roleId = Integer.parseInt(request.getParameter("role"));
            int departmentId = Integer.parseInt(request.getParameter("department"));

            String managerParam = request.getParameter("manager");
            Integer managerId = (managerParam != null && !managerParam.isEmpty()) ? Integer.parseInt(managerParam) : null;

            Employee employee = dao.getEmployee(id);
            employee.setName(name);
            employee.setSalary(salary);
            employee.setRoleId(roleId);
            employee.setDepartmentId(departmentId);
            employee.setReportingManager(managerId);

            dao.updateEmployee(employee);
        }

        response.sendRedirect("EmployeeListServlet");
    }
}

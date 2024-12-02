<%@ page import="java.util.List"%>
<%@ page import="model.Role"%>
<%@ page import="model.Department"%>
<%@ page import="model.Employee"%>

<%
    Employee employee = (Employee) request.getAttribute("employee");
    List<Role> roles = (List<Role>) request.getAttribute("roles");
    List<Department> departments = (List<Department>) request.getAttribute("departments");
    List<Employee> managers = (List<Employee>) request.getAttribute("managers");
    
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Employee Details</h2>
        <div class="card">
            <div class="card-header">
                <h5 class="card-title">Update Employee Information</h5>
            </div>
            <div class="card-body">
                <form action="EmployeeDetailsServlet" method="post">
                    <input type="hidden" name="id" value="<%= employee.getId() %>">

                    <div class="mb-3">
                        <label for="name" class="form-label">Name:</label>
                        <input type="text" name="name" class="form-control" value="<%= employee.getName() %>" required>
                    </div>

                    <div class="mb-3">
                        <label for="salary" class="form-label">Salary:</label>
                        <input type="number" name="salary" class="form-control" value="<%= employee.getSalary() %>" required>
                    </div>

                    <!-- Role Dropdown -->
                    <div class="form-group mt-3">
                        <label for="role">Role:</label>
                        <select id="role" name="role" class="form-control" required>
                            <% for (Role role : roles) { %>
                                <option value="<%= role.getRoleId() %>" <%= (role.getRoleId() == employee.getRoleId()) ? "selected" : "" %>>
                                    <%= role.getRoleName() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <!-- Department Dropdown -->
                    <div class="form-group mt-3">
                        <label for="department">Department:</label>
                        <select id="department" name="department" class="form-control" required>
                            <% for (Department department : departments) { %>
                                <option value="<%= department.getDepartmentId() %>" <%= (department.getDepartmentId() == employee.getDepartmentId()) ? "selected" : "" %>>
                                    <%= department.getDepartmentName() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

    
                    <div class="form-group mt-3">
                        <label for="manager">Manager:</label>
                        <select id="manager" name="manager" class="form-control">
                            <% for (Employee manager : managers) { %>
                                <option value="<%= manager.getId() %>" <%= (manager.getId() == employee.getReportingManager()) ? "selected" : "" %>>
                                    <%= manager.getName() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <button type="submit" name="update" class="btn btn-primary">Update</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmationModal">Delete</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Confirmation Modal -->
    <div class="modal fade" id="confirmationModal" tabindex="-1" aria-labelledby="confirmationModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content rounded-3 shadow">
                <div class="modal-body p-4 text-center">
                    <h5 class="mb-0">Are you sure you want to delete this employee?</h5>
                    <p class="mb-0">This action cannot be undone.</p>
                </div>
                <div class="modal-footer flex-nowrap p-0">
                    <form action="EmployeeDetailsServlet" method="post">
                        <input type="hidden" name="id" value="<%= employee.getId() %>">
                        <input type="submit" name="delete" value="Delete" class="btn btn-lg btn-danger col-6 py-3 m-0 rounded-0">
                    </form>
                    <button type="button" class="btn btn-lg btn-secondary col-6 py-3 m-0 rounded-0" data-bs-dismiss="modal">No</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


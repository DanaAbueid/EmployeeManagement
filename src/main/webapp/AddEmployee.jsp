<%@ page import="java.util.List" %>
<%@ page import="model.Role" %>
<%@ page import="model.Department" %>
<%@ page import="model.Employee" %>
<%
    List<Role> roles = (List<Role>) request.getAttribute("roles");
    List<Department> departments = (List<Department>) request.getAttribute("departments");
    List<Employee> managers = (List<Employee>) request.getAttribute("managers");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Add New Employee</h2>

        <div class="card">
            <div class="card-body">
                <form action="AddEmployeeServlet" method="post">
                    <!-- Employee Name -->
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" class="form-control" required>
                    </div>

                    <!-- Salary -->
                    <div class="form-group mt-3">
                        <label for="salary">Salary:</label>
                        <input type="number" id="salary" name="salary" class="form-control" required>
                    </div>

                    <!-- Role Dropdown -->
                    <div class="form-group mt-3">
                        <label for="role">Role:</label>
                        <select id="role" name="role" class="form-control" required>
                            <% for (Role role : roles) { %>
                                <option value="<%= role.getRoleId() %>"><%= role.getRoleName() %></option>
                            <% } %>
                        </select>
                    </div>

                    <!-- Department Dropdown -->
                    <div class="form-group mt-3">
                        <label for="department">Department:</label>
                        <select id="department" name="department" class="form-control" required>
                            <% for (Department department : departments) { %>
                                <option value="<%= department.getDepartmentId() %>"><%= department.getDepartmentName() %></option>
                            <% } %>
                        </select>
                    </div>

                    <!-- Manager Dropdown -->
                    <div class="form-group mt-3">
                        <label for="manager">Manager:</label>
                        <select id="manager" name="manager" class="form-control">
                            <option value="">None</option>
                            <% for (Employee emp : managers) { %>
                                <option value="<%= emp.getId() %>"><%= emp.getName() %></option>
                            <% } %>
                        </select>
                    </div>

                    <!-- Submit Button -->
                    <div class="mt-4">
                        <button type="submit" class="btn btn-primary">Add Employee</button>
                        <a href="EmployeeListServlet" class="btn btn-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


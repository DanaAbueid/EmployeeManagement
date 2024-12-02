package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import utility.DatabaseUtil;

public class EmployeeDao {

	public List<Employee> getEmployeeList() {
		String sql = "select * from employees where isDeleted = false AND employee_id !=0 ";
		List<Employee> employeeList = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("employee_id"));
				employee.setName(rs.getString("name"));
				employee.setReportingManager(rs.getInt("manager"));
				employee.setSalary(rs.getInt("salary"));
				employee.setDepartmentId(rs.getInt("department_id"));
				employee.setRoleId(rs.getInt("Role_id"));
				employee.setDeleted(rs.getBoolean("isDeleted"));
				employeeList.add(employee);
			}

		} catch (SQLException e) {
			System.out.println("Error fetching employees");
		}

		return employeeList;

	}
	

	public Employee getEmployee(int id) {
		String sql = "select * from employees where employee_id = ? ";
		Employee employee = new Employee();
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery() ;
			while (rs.next()) {
				
				employee.setId(rs.getInt("employee_id"));
				employee.setName(rs.getString("name"));
				employee.setReportingManager(rs.getInt("manager"));
				employee.setSalary(rs.getInt("salary"));
				employee.setDepartmentId(rs.getInt("department_id"));
				employee.setRoleId(rs.getInt("Role_id"));
				employee.setDeleted(rs.getBoolean("isDeleted"));
			}

		} catch (SQLException e) {
			System.out.println("Error fetching employees");
		}

		return employee;

	}
	
	
	
	public List<Employee> getManagersList() {
		String sql = "select * from employees where ( Role_id = 1 OR Role_id = 0 ) AND isDeleted = false";
		List<Employee> employeeList = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("employee_id"));
				employee.setName(rs.getString("name"));
				employee.setReportingManager(rs.getInt("manager"));
				employee.setSalary(rs.getInt("salary"));
				employee.setDepartmentId(rs.getInt("department_id"));
				employee.setRoleId(rs.getInt("Role_id"));
				employee.setDeleted(rs.getBoolean("isDeleted"));
				employeeList.add(employee);
			}

		} catch (SQLException e) {
			System.out.println("Error fetching employees");
		}

		return employeeList;

	}
	
	
	public void addEmployee(Employee employee) {
		String sql = "insert into employees (name , salary , department_id , Role_id, manager , isDeleted) values(?,?,?,?,?,?) ";
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
		
				ps.setString(1,employee.getName());
				ps.setDouble(2,employee.getSalary());
				ps.setInt(3, employee.getDepartmentId());
				ps.setInt(4, employee.getRoleId());
				ps.setInt(5, employee.getReportingManager());
				ps.setBoolean(6, false);
				ps.executeUpdate();
			} catch (SQLException e) {
			System.out.println("Error adding employees");
		}

	}
	
	
	
	
	public void deleteEmployee(int id) {
		String sql = "update employees set isDeleted = true where employee_id = ?  ";
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
				ps.setInt(1,id);
				ps.executeUpdate();

			} catch (SQLException e) {
			System.out.println("Error deleting employees");
		}

	}
	
	
	
	public void updateEmployee(Employee employee) {
		String sql = "UPDATE employees SET name = ?, salary = ?, role_id = ?, department_id = ?, manager = ? WHERE employee_id = ?";
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
			) {
		
				ps.setString(1,employee.getName());
				ps.setDouble(2,employee.getSalary());
				ps.setInt(3, employee.getRoleId());
				ps.setInt(4, employee.getDepartmentId());
				ps.setInt(5, employee.getReportingManager());
				ps.setInt(6, employee.getId());
				ps.executeUpdate();

			} catch (SQLException e) {
			    e.printStackTrace(); 
			System.out.println("Error updating employees");
		}


	}
	
	
}

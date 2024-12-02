package model;

public class Employee {
	private int id;
	private String name;
	private int reportingManager;
	private double salary;
	private boolean isDeleted ;
    private int roleId;
    private int departmentId;

	public Employee() {

		}


	public Employee(String name, int reportingManager, double salary, boolean isDeleted, int roleId, int departmentId) {
		super();
		this.name = name;
		this.reportingManager = reportingManager;
		this.salary = salary;
		this.isDeleted = isDeleted;
		this.roleId = roleId;
		this.departmentId = departmentId;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(int manager) {
		this.reportingManager = manager;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary2) {
		this.salary = salary2;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", Manager=" + reportingManager + ", salary=" + salary + "]";
	}

}

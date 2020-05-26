package ex2;

public class Company {
    private Employee [] employees;
    private static int addedEmployees = 0;

    public Company(int numberOfEmployees) {
        this.employees = new Employee[numberOfEmployees];
    }

    public void addEmployee(Employee employee) {
        employees[addedEmployees] = employee;
        addedEmployees++;
    }

    public int getPerMonthTotalSalary() {
        int totalSalary = 0;
        for (int i = 0; i < addedEmployees; i++) {
            totalSalary += employees[i].getSalary();
        }
        return totalSalary;
    }
}

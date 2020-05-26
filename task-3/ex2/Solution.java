package ex2;

public class Solution {
    public static void main(String[] args) {
        Company company = new Company(6);
        company.addEmployee(new Employee("Constantine", 120000, Position.DIRECTOR));
        company.addEmployee(new Employee("Victor", 100000, Position.TEAM_LEADER));
        company.addEmployee(new Employee("Roman", 70000, Position.QA_ENGINEER));
        company.addEmployee(new Employee("Alex", 90000, Position.SENIOR_DEVELOPER));
        company.addEmployee(new Employee("Olga", 60000, Position.MIDDLE_DEVELOPER));
        company.addEmployee(new Employee("Vasiliy", 40000, Position.JUNIOR_DEVELOPER));

        System.out.println("Monthly salary of all employees: " + company.getPerMonthTotalSalary());
    }
}
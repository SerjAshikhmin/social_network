package ex2;

import ex2.develop.*;

public class Solution {
    public static void main(String[] args) {
        Company company = new Company(6);
        company.addEmployee(new Director("Constantine", 120000));
        company.addEmployee(new TeamLeader("Victor", 100000));
        company.addEmployee(new QAEngineer("Roman", 70000));
        company.addEmployee(new SeniorDeveloper("Alex", 90000));
        company.addEmployee(new MiddleDeveloper("Olga", 60000));
        company.addEmployee(new JuniorDeveloper("Vasiliy", 40000));

        System.out.println("Monthly salary of all employees: " + company.getPerMonthTotalSalary());
    }
}
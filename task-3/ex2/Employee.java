package ex2;

public class Employee {
    private String name;
    private int salary;
    private Position position;

    public Employee(String name, int salary, Position position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Position getPosition() {
        return position;
    }
}

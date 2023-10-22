package ru.appline.logic;

public class User {
    private String name;
    private String surname;
    private double salary;


    public User(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean validateFields() {
        if (name.isEmpty() || surname.isEmpty() || name.matches(".*\\d.*") || surname.matches(".*\\d.*")) {
            return false;
        }
        if (salary < 0) {
            return false;
        }
        return true;
    }

}

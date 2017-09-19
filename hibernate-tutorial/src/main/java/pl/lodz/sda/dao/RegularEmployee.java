package pl.lodz.sda.dao;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("regularemployee")
public class RegularEmployee extends Employee {

    @Column(name = "salary")
    int salary;

    public RegularEmployee() {
    }

    public RegularEmployee(String name) {
        super(name);
    }

    public RegularEmployee(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "RegularEmployee{" +
                "salary=" + salary +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
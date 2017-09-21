package pl.lodz.sda.model;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
//@DiscriminatorValue("contractemployee")
//@PrimaryKeyJoinColumn(name="ID")
public class ContractEmployee extends Employee {

    @Column(name = "hour_rate")
    int hourRate;

    public ContractEmployee() {
    }

    public ContractEmployee(String name, int hourRate) {
        super(name);
        this.hourRate = hourRate;
    }

    public int getHourRate() {
        return hourRate;
    }

    public void setHourRate(int hourRate) {
        this.hourRate = hourRate;
    }

    @Override
    public String toString() {
        return "ContractEmployee{" +
                "hourRate=" + hourRate +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

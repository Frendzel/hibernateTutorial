package pl.lodz.sda.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "company_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "company",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Department> department;

    @OneToOne(mappedBy = "company",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Address address;

    public Company() {
    }

    public Company(String name, Set<Department> department, Address address) {
        this.name = name;
        this.department = department;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Department> getDepartment() {
        return department;
    }

    public void setDepartment(Set<Department> department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                (id != null ? "id=" + id : "") +
                (name != null ? ", name='" + name + '\'' : "") +
                (department != null ? ", department=" + department : "") +
                (address != null ? ", address=" + address : "") +
                '}';
    }
}

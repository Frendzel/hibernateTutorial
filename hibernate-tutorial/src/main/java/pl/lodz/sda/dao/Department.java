package pl.lodz.sda.dao;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "department_id")
    Long id;

    @Column(name = "name")
    String name;

    @Embedded
    DepartmentAdress departmentAdress;

    // joining an entity association or an embeddable collection.
    //The @JoinColumn annotation is used to specify the FOREIGN KEY column used when
    @ManyToOne
    @JoinColumn(name = "company_id",
            nullable = true)
    Company company;

    public Department() {
    }

    public Department(Company company) {
        this.company = company;
    }

    public Department(Long id, String name, Company company, DepartmentAdress departmentAdress) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.departmentAdress = departmentAdress;
    }

    public DepartmentAdress getDepartmentAdress() {
        return departmentAdress;
    }

    public void setDepartmentAdress(DepartmentAdress departmentAdress) {
        this.departmentAdress = departmentAdress;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company.getName() +
                ", departmentAdress=" + departmentAdress.toString() +
                '}';
    }
}

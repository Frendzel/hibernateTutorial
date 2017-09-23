package pl.lodz.sda.service;

import pl.lodz.sda.dao.CompanyManager;
import pl.lodz.sda.model.Company;

import java.io.IOException;
import java.util.List;

public class CompanyService {

    CompanyManager companyManager;

    public CompanyService() {
        try {
            this.companyManager = new CompanyManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Company saveCompany(Company company) {
        return companyManager.save(company);
    }

    public Company findFirstCompany() {
        return companyManager.find(1l);
    }
    public Company findCompany(long idCompany) {
        return companyManager.find(idCompany);
    }
    public List<Company> findAll() {
        return companyManager.findAll();
    }

    public Company delete(long l) {
        return companyManager.delete(l);
    }
}

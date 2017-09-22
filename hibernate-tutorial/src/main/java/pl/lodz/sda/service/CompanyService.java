package pl.lodz.sda.service;

import pl.lodz.sda.dao.CompanyManager;
import pl.lodz.sda.model.Company;

import java.io.IOException;

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
}

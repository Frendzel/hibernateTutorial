package pl.lodz.sda.runner;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.sda.model.Company;
import pl.lodz.sda.service.CompanyService;

import java.util.List;

import static java.lang.Long.parseLong;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class CompanyController {

    CompanyService companyService = new CompanyService();

    @RequestMapping(path = "/company/first")
    public Company getFirstCompany() {
        return companyService.findFirstCompany();
    }

    @RequestMapping(path = "/company/all")
    public List<Company> getAllCompany() {
        return companyService.findAll();
    }

    @RequestMapping(path = "/company")
    public Company getCompany(@RequestParam("id") String param) {
        return companyService.findCompany(parseLong(param));
    }

    @RequestMapping(path = "/company", method = DELETE)
    public Company deleteCompany(@RequestParam("id") String param) {
        return companyService.delete(parseLong(param));
    }

    @RequestMapping(path = "/company/save",
            method = POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public Company save(@RequestBody Company company) {
        return companyService.saveCompany(company);
    }

}

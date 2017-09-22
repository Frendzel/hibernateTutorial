package pl.lodz.sda.runner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.sda.model.Company;
import pl.lodz.sda.service.CompanyService;

@RestController
public class CompanyController {

    CompanyService companyService = new CompanyService();

    @RequestMapping("/company/first")
    public Company getFirstCompany() {
        return companyService.findFirstCompany();
    }

//    @RequestMapping("/")
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }


}

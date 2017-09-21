package pl.lodz.sda.dao;

import org.junit.Before;
import org.junit.Test;
import pl.lodz.sda.model.Company;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CompanyManagerTest {

    CompanyManager companyManager;

    @Before
    public void init() {
        try {
            companyManager = new CompanyManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void save() throws Exception {
        //given
        Company company = new Company();
        //when
        Company savedEntity = companyManager.save(company);
        //then
        assertEquals(savedEntity, company);
    }

    @Test
    public void find() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void deleteAll() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}
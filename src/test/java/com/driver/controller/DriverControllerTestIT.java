package com.driver.controller;
import com.driver.model.Driver;
import com.driver.model.dto.DriverDto;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DriverControllerTestIT {

    private TestRestTemplate restTemplate;

    @Autowired
    public DriverControllerTestIT(TestRestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Test
    public void driverCreatedTestIT(){

        Driver driver = new Driver();
        driver.setLastName("Doe");
        driver.setFirstName("John");
        driver.setCity("San Francisco");
        driver.setState("CA");
        driver.setLicenseNumber("4GJS839");

        Driver response = this.restTemplate.postForObject("/api/v1/drivers/", driver, Driver.class );
//        Driver response = this.restTemplate.getForObject("/api/drivers/", Driver.class);
        System.out.println(response);

        Assertions.assertEquals(driver.getLicenseNumber(), response.getLicenseNumber());


    }

    @Test
    public void driverGetAll() throws JSONException {

        List<DriverDto> response = this.restTemplate.getForObject("/api/v1/drivers/", List.class );
        Assertions.assertNotEquals(0, response.size());

    }
}



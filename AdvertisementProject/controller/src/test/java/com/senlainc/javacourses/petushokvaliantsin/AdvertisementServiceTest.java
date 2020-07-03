package com.senlainc.javacourses.petushokvaliantsin;

import com.senlainc.javacourses.petushokvaliantsin.configuration.AppConfig;
import com.senlainc.javacourses.petushokvaliantsin.configuration.SecurityConfig;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCategoryDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, SecurityConfig.class})
public class AdvertisementServiceTest {

    @Autowired
    private IAdvertisementService advertisementService;
    private AdvertisementDto advertisementDto;

    @Before
    public void setData() {
        this.advertisementDto = new AdvertisementDto();
        final AdvertisementCategoryDto advertisementCategoryDto = new AdvertisementCategoryDto();
        advertisementCategoryDto.setId((long) 1);
        advertisementDto.setHeader("Test data");
        advertisementDto.setDescription("Test data");
        advertisementDto.setDate(LocalDate.now());
        advertisementDto.setPrice(130.0);
        advertisementDto.setAdvertisementCategory(advertisementCategoryDto);
    }

    @After
    public void clearData() {

    }

    @Test
    public void testSuccessfulCreateAdvertisement() {
        Assert.assertTrue(advertisementService.create("admin", advertisementDto));
    }
}

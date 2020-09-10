package com.senlainc.javacourses.petushokvaliantsin;

import com.senlainc.javacourses.petushokvaliantsin.configuration.AppConfig;
import com.senlainc.javacourses.petushokvaliantsin.configuration.SecurityConfig;
import com.senlainc.javacourses.petushokvaliantsin.dto.StateDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCategoryDto;
import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
import com.senlainc.javacourses.petushokvaliantsin.enumeration.EnumState;
import com.senlainc.javacourses.petushokvaliantsin.serviceapi.advertisement.IAdvertisementService;
import com.senlainc.javacourses.petushokvaliantsin.utility.exception.EntityNotExistException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdvertisementServiceTest {

    @Autowired
    private IAdvertisementService advertisementService;

    @Test
    public void successfulCreate() {
        AdvertisementDto advertisementDto = new AdvertisementDto();
        final AdvertisementCategoryDto advertisementCategoryDto = new AdvertisementCategoryDto();
        advertisementCategoryDto.setId((long) 1);
        advertisementDto.setHeader("Test data");
        advertisementDto.setDescription("Test data");
        advertisementDto.setDate(LocalDate.now());
        advertisementDto.setPrice(130.0);
        advertisementDto.setAdvertisementCategory(advertisementCategoryDto);
        Assert.assertTrue(advertisementService.create("admin", advertisementDto));
    }

    @Test
    public void successfulUpdateByModerator() {
        final StateDto stateDto = new StateDto();
        stateDto.setDescription("REJECTED");
        Assert.assertTrue(advertisementService.updateStateByModerator((long) 1, stateDto));
    }

    @Test
    public void successfulReadAll() {
        Assert.assertEquals(1,
                advertisementService.readAll(0, 15, "asc", "default", "none",
                        "none", 0, 0, EnumState.ACTIVE).size());
    }

    @Test(expected = EntityNotExistException.class)
    public void wrongIndexRead() {
        advertisementService.readByModerator((long) -2);
    }
}

//package com.senlainc.javacourses.petushokvaliantsin;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.senlainc.javacourses.petushokvaliantsin.controller.AdvertisementController;
//import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementCategoryDto;
//import com.senlainc.javacourses.petushokvaliantsin.dto.advertisement.AdvertisementDto;
//import com.senlainc.javacourses.petushokvaliantsin.service.api.advertisement.AdvertisementService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AdvertisementControllerTest {
//
//    @Mock
//    private AdvertisementService advertisementService;
//    @InjectMocks
//    private AdvertisementController advertisementController;
//    private MockMvc mockMvc;
//    private AdvertisementDto request;
//
//    @Before
//    public void createTestingData() {
//        this.request = new AdvertisementDto();
//        final AdvertisementCategoryDto categoryDto = new AdvertisementCategoryDto();
//        categoryDto.setId(1L);
//        this.request.setHeader("Test data");
//        this.request.setDescription("We testing advertisement controller");
//        this.request.setPrice(450.0);
//        this.request.setAdvertisementCategory(categoryDto);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(advertisementController).build();
//    }
//
//    @Test
//    public void createSuccess() throws Exception {
//        final String string = new ObjectMapper().writeValueAsString(request);
//        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("admin", "admin");
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/advertisements/create")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .principal(token)
//                        .content(string))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        Mockito.verify(advertisementService, Mockito.times(1)).create(token.getName(), request);
//        Mockito.verifyNoInteractions(advertisementService);
//    }
//}

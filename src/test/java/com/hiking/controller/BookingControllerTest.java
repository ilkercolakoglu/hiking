package com.hiking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiking.dto.CancelBookingDTO;
import com.hiking.dto.HikerBookingRequestDTO;
import com.hiking.dto.HikerDTO;
import com.hiking.service.BookingService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @developer -- ilkercolakoglu
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingService bookingService;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void should_create_booking() throws Exception {
        HikerBookingRequestDTO request = createHikerBookingRequestDTO(31);

        String requestBody = this.objectMapper.writeValueAsString(request);

        this.mockMvc.perform(post("/booking/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_get_age_rule_exception() throws Exception {
        HikerBookingRequestDTO request = createHikerBookingRequestDTO(3);

        String requestBody = this.objectMapper.writeValueAsString(request);

        this.mockMvc.perform(post("/booking/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_get_no_tour_found_exception() throws Exception {
        HikerBookingRequestDTO request = createHikerBookingRequestDTO(31);
        request.setTourId(5L);

        String requestBody = this.objectMapper.writeValueAsString(request);

        this.mockMvc.perform(post("/booking/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isNotFound());
    }


    @Test
    public void should_cancel_booking() throws Exception {
        // create an example booking
        String bookingNumber = createBooking();


        // cancel it
        CancelBookingDTO cancelBookingDTO = createCancelBookingDTO();
        cancelBookingDTO.setBookingNumber(bookingNumber);

        String cancelBookingDTOBody = this.objectMapper.writeValueAsString(cancelBookingDTO);


        this.mockMvc.perform(post("/booking/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cancelBookingDTOBody))
                .andExpect(status().isOk());
    }

    @Test
    public void should_cancel_surname_exception() throws Exception {
        // create an example booking
        String bookingNumber = createBooking();

        // cancel it
        CancelBookingDTO cancelBookingDTO = createCancelBookingDTO();
        cancelBookingDTO.setBookingNumber(bookingNumber);
        cancelBookingDTO.setSurname("wrongSurname");

        String cancelBookingDTOBody = this.objectMapper.writeValueAsString(cancelBookingDTO);


        this.mockMvc.perform(post("/booking/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cancelBookingDTOBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_get_no_booking_found_exception_in_cancel() throws Exception {
        // don't create any booking

        // cancel it
        CancelBookingDTO cancelBookingDTO = createCancelBookingDTO();
        cancelBookingDTO.setBookingNumber("anyBookingNumber");

        String cancelBookingDTOBody = this.objectMapper.writeValueAsString(cancelBookingDTO);


        this.mockMvc.perform(post("/booking/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cancelBookingDTOBody))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_view_bookings_with_a_date() throws Exception {
        // create an example booking
        String bookingNumber = createBooking();

        this.mockMvc.perform(get("/booking/view_bookings_with_date")
                .param("localDate","02/12/2020"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_no_booking_found_exception_in_view_booking_in_a_date() throws Exception {
        // don't create any booking

        // try it
        this.mockMvc.perform(get("/booking/view_bookings_with_date")
                .param("localDate","04/12/2020"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_view_booking() throws Exception {
        // create an example booking
        String bookingNumber = createBooking();

        this.mockMvc.perform(get("/booking/view_booking")
                .param("bookingNumber",bookingNumber))
                .andExpect(status().isOk());
    }

    @Test
    public void should_no_booking_found_exception_in_view_booking() throws Exception {
        // don't create any booking

        // try it
        this.mockMvc.perform(get("/booking/view_booking")
                .param("bookingNumber","anyBookingNumber"))
                .andExpect(status().isNotFound());
    }


    private HikerBookingRequestDTO createHikerBookingRequestDTO(int age) {
        HikerBookingRequestDTO hikerBookingRequestDTO = new HikerBookingRequestDTO();
        hikerBookingRequestDTO.setTourId(1L);
        Set<HikerDTO> hikerSet = new HashSet<>();
        HikerDTO hiker = new HikerDTO();
        hiker.setHikerName("ilker");
        hiker.setHikerSurname("colakoglu");
        hiker.setAge(age);
        hikerSet.add(hiker);
        hikerBookingRequestDTO.setHikerSet(hikerSet);
        return hikerBookingRequestDTO;
    }


    private CancelBookingDTO createCancelBookingDTO() {
        CancelBookingDTO cancelBookingDTO = new CancelBookingDTO();
        cancelBookingDTO.setBookingNumber("12345678");
        cancelBookingDTO.setSurname("colakoglu");
        return cancelBookingDTO;
    }

    private String createBooking() throws Exception {
        HikerBookingRequestDTO request = createHikerBookingRequestDTO(31);

        String requestBody = this.objectMapper.writeValueAsString(request);

        MvcResult mvcResult = this.mockMvc.perform(post("/booking/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
        return (String) jsonObject.get("bookingNumber");
    }

}

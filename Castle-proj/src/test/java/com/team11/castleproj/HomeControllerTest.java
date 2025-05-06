//package com.team11.castleproj;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(HomeControllerTest.class)
//public class HomeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testGreet() throws Exception {
//        mockMvc.perform(get("/itinerary")
//                        .param("name", "World")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .andExpect(status().isOk())
//                        .andExpect(content().string("Hello, World!"));
//    }
//}

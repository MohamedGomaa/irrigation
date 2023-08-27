package com.digitalfactory.irrigation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.digitalfactory.irrigation.payload.AddPlotRequest;
import com.digitalfactory.irrigation.service.LandPlotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LandPlotController.class)
public class LandPlotControllerIT {
  public final String API_PATH = "http://localhost:8088/api/v1/plots";
  @Autowired
  MockMvc mockMvc;
  @MockBean
  LandPlotService plotService;

  @Test
  void whenAddNewPlotThenSuccess() throws Exception {
    AddPlotRequest addPlotRequest = new AddPlotRequest(1234L, 3.5);

    mockMvc.perform(
        post(API_PATH)
            .content(asJsonString(addPlotRequest))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}

package com.digitalfactory.irrigation.service;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.digitalfactory.irrigation.exception.IrrigationServiceException;
import com.digitalfactory.irrigation.model.LandPlot;
import com.digitalfactory.irrigation.payload.AddPlotRequest;
import com.digitalfactory.irrigation.payload.UpdatePlotRequest;
import com.digitalfactory.irrigation.repository.LandPlotRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.hibernate.action.internal.EntityActionVetoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class LandPlotServiceTest {

  @Mock
  LandPlotRepository plotRepositoryMock;
  @InjectMocks
  LandPlotService plotService;

  @BeforeEach
  void setup() {
    plotRepositoryMock = Mockito.mock(LandPlotRepository.class);
    plotService = new LandPlotServiceImpl(plotRepositoryMock);
  }

  @Test
  void whenGetPlotByIdThenSuccess() {
    long plotId = 1234L;
    when(plotRepositoryMock.findById(plotId)).thenReturn(Optional.of(getLandPlot()));

    LandPlot plot = plotService.getPlotById(plotId);

    assertNotNull(plot);
  }

  @Test
  void whenGetPlotByIdThenFail() {
    long plotId = 0000L;
    when(plotRepositoryMock.findById(plotId)).thenThrow(EntityNotFoundException.class);
    assertThrows(IrrigationServiceException.class, () -> plotService.getPlotById(plotId));
  }

  @Test
  void whenAddNewPlotThenSuccess() {
    AddPlotRequest addPlotRequest = new AddPlotRequest(1234L, 3.5);

    when(plotRepositoryMock.save(any())).thenReturn(getLandPlot());

    LandPlot plot = plotService.addNewPlot(addPlotRequest);

    assertEquals(addPlotRequest.getPlotNumber(), plot.getPlotNumber());
    assertEquals(addPlotRequest.getAmountOfWater(), plot.getAmountOfWater());
  }

  @Test
  void whenUpdatePlotThenSuccess() {
    long plotId = 1234L;
    UpdatePlotRequest updatePlotRequest = new UpdatePlotRequest(5678L, 5.2);

    when(plotRepositoryMock.findById(plotId)).thenReturn(Optional.of(getLandPlot()));
    when(plotRepositoryMock.save(any())).thenReturn(getUpdatedLandPlot());

    LandPlot plot = plotService.updatePlot(plotId, updatePlotRequest);

    assertEquals(updatePlotRequest.getPlotNumber(), plot.getPlotNumber());
    assertEquals(updatePlotRequest.getAmountOfWater(), plot.getAmountOfWater());
  }

  @Test
  void whenGetAllPlotsThenSuccess() {
    when(plotRepositoryMock.findAll()).thenReturn(List.of(getLandPlot()));

    List<LandPlot> plotsList = plotService.getAllPlots();

    assertEquals(1, plotsList.size());
  }

  LandPlot getLandPlot() {
    return LandPlot.builder().id(1234L).plotNumber(1234L).amountOfWater(3.5).build();
  }

  LandPlot getUpdatedLandPlot() {
    return LandPlot.builder().id(1234L).plotNumber(5678L).amountOfWater(5.2).build();
  }
}

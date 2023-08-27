package com.digitalfactory.irrigation.service;

import com.digitalfactory.irrigation.exception.IrrigationServiceException;
import com.digitalfactory.irrigation.model.LandPlot;
import com.digitalfactory.irrigation.payload.AddPlotRequest;
import com.digitalfactory.irrigation.payload.UpdatePlotRequest;
import com.digitalfactory.irrigation.repository.LandPlotRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LandPlotServiceImpl implements LandPlotService {

  private final LandPlotRepository plotRepository;

  @Override
  public LandPlot getPlotById(long plotId) {
    try {
      return plotRepository.findById(plotId).orElseThrow(
          () -> new EntityNotFoundException("There is no plot with id: [ " + plotId + " ]"));
    } catch (Exception e) {
      log.error("Cannot find plot with id: [ {} ], due to: [ {} ]", plotId, e.getMessage());
      throw new IrrigationServiceException("Cannot find plot with id: [ " + plotId + " ]");
    }
  }

  @Override
  public LandPlot addNewPlot(AddPlotRequest addPlotRequest) {
    try {
      return plotRepository.save(
          LandPlot.builder().plotNumber(addPlotRequest.getPlotNumber())
              .amountOfWater(addPlotRequest.getAmountOfWater()).build());
    } catch (Exception e) {
      log.error("Cannot save plot: [ {} ], due to: [ {} ]", addPlotRequest, e.getMessage());
      throw new IrrigationServiceException("Cannot save plot: [ " + addPlotRequest + " ]");
    }
  }

  @Override
  public boolean deletePlotById(long plotId){
    try {
      plotRepository.deleteById(plotId);
      return true;
    } catch (Exception e) {
      log.error("Cannot delete plot with id: [ {} ], due to: [ {} ]", plotId, e.getMessage());
      throw new IrrigationServiceException("Cannot delete plot with id: [ " + plotId + " ]");
    }
  }

  @Override
  public LandPlot updatePlot(long plotId, UpdatePlotRequest updatePlot) {
    try {
      LandPlot plot = getPlotById(plotId);
      if(updatePlot != null){
        if(updatePlot.getPlotNumber() != null){
          plot.setPlotNumber(updatePlot.getPlotNumber());
        }
        if (updatePlot.getAmountOfWater() != null){
          plot.setAmountOfWater(updatePlot.getAmountOfWater());
        }
      }
      return plotRepository.save(plot);
    } catch (Exception e) {
      log.error("Cannot update plot with id: [ {} ], due to: [ {} ]", plotId, e.getMessage());
      throw new IrrigationServiceException("Cannot update plot with id: [ " + plotId + " ]");
    }
  }

  @Override
  public List<LandPlot> getAllPlots(){
    try{
      return plotRepository.findAll();
    }catch (Exception e){
      log.error("Cannot retrieve all plots due to: [ {} ]", e.getMessage());
      throw new IrrigationServiceException("Cannot retrieve all plots!");
    }
  }
}

package com.digitalfactory.irrigation.controller;

import static com.digitalfactory.irrigation.controller.LandPlotController.API_PATH;

import com.digitalfactory.irrigation.payload.AddPlotRequest;
import com.digitalfactory.irrigation.payload.UpdatePlotRequest;
import com.digitalfactory.irrigation.service.LandPlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_PATH)
@RequiredArgsConstructor
public class LandPlotController {
  public static final String API_PATH = "api/v1/plots";

  private final LandPlotService plotService;

  @GetMapping
  public ResponseEntity<?> getAllPlots(){
    return new ResponseEntity<>(plotService.getAllPlots(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> addNewPlot(@RequestBody AddPlotRequest addPlotRequest){
    return new ResponseEntity<>(plotService.addNewPlot(addPlotRequest), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getPlotById(@PathVariable("id") long plotId){
    return new ResponseEntity<>(plotService.getPlotById(plotId), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePlotById(@PathVariable("id") long plotId){
    return new ResponseEntity<>(plotService.getPlotById(plotId), HttpStatus.NO_CONTENT);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updatePlot(@PathVariable("id") long plotId,
      @RequestBody UpdatePlotRequest updatePlotRequest){
    return new ResponseEntity<>(plotService.updatePlot(plotId, updatePlotRequest), HttpStatus.ACCEPTED);
  }

}

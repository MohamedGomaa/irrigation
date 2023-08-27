package com.digitalfactory.irrigation.service;

import com.digitalfactory.irrigation.model.LandPlot;
import com.digitalfactory.irrigation.payload.AddPlotRequest;
import com.digitalfactory.irrigation.payload.UpdatePlotRequest;
import java.util.List;

public interface LandPlotService {

  LandPlot getPlotById(long plotId);

  LandPlot addNewPlot(AddPlotRequest addPlotRequest);

  boolean deletePlotById(long plotId);

  LandPlot updatePlot(long plotId, UpdatePlotRequest updatePlot);

  List<LandPlot> getAllPlots();
}

package com.digitalfactory.irrigation.repository;

import com.digitalfactory.irrigation.model.LandPlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandPlotRepository extends JpaRepository<LandPlot, Long> {

}

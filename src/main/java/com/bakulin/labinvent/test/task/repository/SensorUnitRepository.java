package com.bakulin.labinvent.test.task.repository;

import com.bakulin.labinvent.test.task.model.entity.SensorUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorUnitRepository extends JpaRepository<SensorUnit, String> {
    SensorUnit findSensorUnitBySensorUnitName(String sensorUnitName);
}

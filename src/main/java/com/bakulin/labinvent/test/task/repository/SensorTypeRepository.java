package com.bakulin.labinvent.test.task.repository;

import com.bakulin.labinvent.test.task.model.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTypeRepository extends JpaRepository<SensorType, String> {
    SensorType findSensorTypeBySensorTypeName(String sensorTypeName);
}

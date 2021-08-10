package com.bakulin.labinvent.test.task.repository;

import com.bakulin.labinvent.test.task.model.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Sensor findSensorBySensorName(String sensorName);

    Sensor findSensorBySensorModel(String sensorModel);

    Sensor findSensorBySensorType(String sensorType);
}

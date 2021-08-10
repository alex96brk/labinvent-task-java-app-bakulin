package com.bakulin.labinvent.test.task.service;

import com.bakulin.labinvent.test.task.exception.sensor.SensorModelExistsException;
import com.bakulin.labinvent.test.task.exception.sensor.SensorNameExistsException;
import com.bakulin.labinvent.test.task.exception.sensor.SensorNotFoundException;
import com.bakulin.labinvent.test.task.model.dto.SensorDTO;
import com.bakulin.labinvent.test.task.model.dto.SensorTypeDTO;
import com.bakulin.labinvent.test.task.model.dto.SensorUnitDTO;
import com.bakulin.labinvent.test.task.model.entity.Sensor;
import com.bakulin.labinvent.test.task.model.entity.SensorType;
import com.bakulin.labinvent.test.task.model.entity.SensorUnit;

import java.util.List;
import java.util.Optional;

public interface SensorService {

    Sensor addNewSensor(String sensorName, String sensorModel, Double leftRange,
                        Double rightRange, SensorType sensorType, SensorUnit sensorUnit,
                        String sensorLocation, String sensorDescription) throws SensorNotFoundException, SensorModelExistsException, SensorNameExistsException;

    List<SensorDTO> getAllSensors();

    Sensor findSensorByName(String sensorName);

    Sensor findSensorByModel(String sensorModel);

    Sensor findSensorByType(String sensorType);

    Sensor updateSensor(String currentSensorName, String newSensorName, String newSensorModel,
                        Double newLeftRange, Double newRightRange, SensorType newSensorType, SensorUnit newSensorUnit,
                        String newSensorLocation, String newSensorDescription) throws SensorNotFoundException, SensorModelExistsException, SensorNameExistsException;

    void deleteSensor(String sensorName);

    SensorType findSensorTypeById(String sensorTypeName);

    SensorUnit findSensorUnitById(String sensorUnitName);

    List<SensorTypeDTO> findAllSensorTypes();

    List<SensorUnitDTO> findAllSensorUnits();


}

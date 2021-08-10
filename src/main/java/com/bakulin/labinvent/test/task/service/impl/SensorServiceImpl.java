package com.bakulin.labinvent.test.task.service.impl;

import com.bakulin.labinvent.test.task.constant.SensorConstant;
import com.bakulin.labinvent.test.task.exception.sensor.SensorModelExistsException;
import com.bakulin.labinvent.test.task.exception.sensor.SensorNameExistsException;
import com.bakulin.labinvent.test.task.exception.sensor.SensorNotFoundException;
import com.bakulin.labinvent.test.task.model.dto.SensorDTO;
import com.bakulin.labinvent.test.task.model.dto.SensorTypeDTO;
import com.bakulin.labinvent.test.task.model.dto.SensorUnitDTO;
import com.bakulin.labinvent.test.task.model.entity.Sensor;
import com.bakulin.labinvent.test.task.model.entity.SensorType;
import com.bakulin.labinvent.test.task.model.entity.SensorUnit;
import com.bakulin.labinvent.test.task.repository.*;
import com.bakulin.labinvent.test.task.service.SensorService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bakulin.labinvent.test.task.constant.SensorConstant.*;


@Service
@Transactional
@Qualifier("sensorService")
public class SensorServiceImpl implements SensorService {

    private SensorRepository sensorRepository;
    private SensorTypeRepository sensorTypeRepository;
    private SensorUnitRepository sensorUnitRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository,
                             SensorTypeRepository sensorTypeRepository,
                             SensorUnitRepository sensorUnitRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorTypeRepository = sensorTypeRepository;
        this.sensorUnitRepository = sensorUnitRepository;

    }

    @Override
    public Sensor addNewSensor(String sensorName, String sensorModel,
                               Double leftRange, Double rightRange,
                               SensorType sensorType, SensorUnit sensorUnit,
                               String sensorLocation, String sensorDescription) throws SensorNotFoundException, SensorModelExistsException, SensorNameExistsException {
        validateNewSensorNameAndModel(EMPTY, sensorName, sensorModel);
        Sensor newSensor = new Sensor();
        newSensor.setSensorId(generateSensorId());
        newSensor.setSensorName(sensorName);
        newSensor.setSensorModel(sensorModel);
        newSensor.setLeftRange(leftRange);
        newSensor.setRightRange(rightRange);
        newSensor.setSensorType(sensorType);
        newSensor.setSensorUnit(sensorUnit);
        newSensor.setSensorLocation(sensorLocation);
        newSensor.setSensorDescription(sensorDescription);
        sensorRepository.save(newSensor);
        return newSensor;
    }

    @Override
    public List<SensorDTO> getAllSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        List<SensorDTO> sensorDTOS = new ArrayList<>();
        for (Sensor sensor:
                sensors) {
            SensorDTO sesnorDTO = new SensorDTO();
            sesnorDTO.setSensorId(sensor.getSensorId());
            sesnorDTO.setSensorName(sensor.getSensorName());
            sesnorDTO.setSensorModel(sensor.getSensorModel());
            sesnorDTO.setLeftRange(sensor.getLeftRange());
            sesnorDTO.setRightRange(sensor.getRightRange());
            sesnorDTO.setSensorType(sensor.getSensorType().getSensorTypeName());
            sesnorDTO.setSensorUnit(sensor.getSensorUnit().getSensorUnitName());
            sesnorDTO.setSensorLocation(sensor.getSensorLocation());
            sesnorDTO.setSensorDescription(sensor.getSensorDescription());
            sensorDTOS.add(sesnorDTO);
        }

        return sensorDTOS;
    }

    @Override
    public Sensor findSensorByName(String sensorName) {
        return sensorRepository.findSensorBySensorName(sensorName);
    }

    @Override
    public Sensor findSensorByModel(String sensorModel) {
        return sensorRepository.findSensorBySensorModel(sensorModel);
    }

    @Override
    public Sensor findSensorByType(String sensorType) {
        return sensorRepository.findSensorBySensorType(sensorType);
    }

    @Override
    public Sensor updateSensor(String currentSensorName, String newSensorName, String newSensorModel, Double newLeftRange, Double newRightRange, SensorType newSensorType, SensorUnit newSensorUnit, String newSensorLocation, String newSensorDescription) throws SensorNotFoundException, SensorModelExistsException, SensorNameExistsException {
        Sensor sensorUpdate = validateNewSensorNameAndModel(currentSensorName, newSensorName, newSensorModel);
        sensorUpdate.setSensorName(newSensorName);
        sensorUpdate.setSensorModel(newSensorModel);
        sensorUpdate.setLeftRange(newLeftRange);
        sensorUpdate.setRightRange(newRightRange);
        sensorUpdate.setSensorType(newSensorType);
        sensorUpdate.setSensorUnit(newSensorUnit);
        sensorUpdate.setSensorLocation(newSensorLocation);
        sensorUpdate.setSensorDescription(newSensorDescription);
        sensorRepository.save(sensorUpdate);
        return sensorUpdate;
    }

    @Override
    public void deleteSensor(String sensorName) {
        Sensor sensorDelete = sensorRepository.findSensorBySensorName(sensorName);
        sensorRepository.deleteById(sensorDelete.getId());
    }

    @Override
    public SensorType findSensorTypeById(String sensorTypeName) {
        return sensorTypeRepository.findSensorTypeBySensorTypeName(sensorTypeName);
    }

    @Override
    public SensorUnit findSensorUnitById(String sensorUnitName) {
        return sensorUnitRepository.findSensorUnitBySensorUnitName(sensorUnitName);
    }


    @Override
    public List<SensorTypeDTO> findAllSensorTypes() {
        List<SensorType> sensorTypes = sensorTypeRepository.findAll();
        List<SensorTypeDTO> sensorTypeDTOS = new ArrayList<>();
        for (SensorType sensorType :
             sensorTypes) {
            SensorTypeDTO sensorTypeDTO = new SensorTypeDTO();
            sensorTypeDTO.setSensorType(sensorType.getSensorTypeName());
            sensorTypeDTOS.add(sensorTypeDTO);
        }
        return sensorTypeDTOS;
    }


    @Override
    public List<SensorUnitDTO> findAllSensorUnits() {
        List<SensorUnit> sensorUnits = sensorUnitRepository.findAll();
        List<SensorUnitDTO> sensorUnitDTOS = new ArrayList<>();
        for (SensorUnit sensorUnit:
             sensorUnits) {
            SensorUnitDTO sensorUnitDTO = new SensorUnitDTO();
            sensorUnitDTO.setSensorUnit(sensorUnit.getSensorUnitName());
            sensorUnitDTOS.add(sensorUnitDTO);
        }
        return sensorUnitDTOS;
    }

    private String generateSensorId() {
        return RandomStringUtils.randomNumeric(8);
    }

    private Sensor validateNewSensorNameAndModel(String currentSensorName, String newSensorName, String newSensorModel) throws SensorNotFoundException, SensorNameExistsException, SensorModelExistsException {
        Sensor sensorByNewSensorName = findSensorByName(newSensorName);
        Sensor sensorByNewSensorModel = findSensorByModel(newSensorModel);

        if (StringUtils.isNotBlank(currentSensorName)) {
            Sensor currentSensor = findSensorByName(currentSensorName);
            if (currentSensor == null) {
                throw new SensorNotFoundException(NO_SENSOR_FOUND_BY_NAME + currentSensorName);
            }
            if (sensorByNewSensorName != null && !currentSensor.getId().equals(sensorByNewSensorName.getId())) {
                throw new SensorNameExistsException(SENSOR_NAME_ALREADY_EXISTS);
            }
            if (sensorByNewSensorModel != null && !currentSensor.getId().equals(sensorByNewSensorModel.getId())) {
                throw new SensorModelExistsException(SENSOR_MODEL_ALREADY_EXISTS);
            }
            return currentSensor;
        } else {
            if (sensorByNewSensorName != null) {
                throw new SensorNameExistsException(SENSOR_NAME_ALREADY_EXISTS);
            }
            if (sensorByNewSensorModel != null) {
                throw new SensorModelExistsException((SENSOR_MODEL_ALREADY_EXISTS));
            }
            return null;
        }

    }


}

package com.bakulin.labinvent.test.task.controller;

import com.bakulin.labinvent.test.task.domain.HttpResponse;
import com.bakulin.labinvent.test.task.exception.ExceptionHandling;
import com.bakulin.labinvent.test.task.exception.sensor.SensorModelExistsException;
import com.bakulin.labinvent.test.task.exception.sensor.SensorNameExistsException;
import com.bakulin.labinvent.test.task.exception.sensor.SensorNotFoundException;
import com.bakulin.labinvent.test.task.model.dto.SensorDTO;
import com.bakulin.labinvent.test.task.model.dto.SensorTypeDTO;
import com.bakulin.labinvent.test.task.model.dto.SensorUnitDTO;
import com.bakulin.labinvent.test.task.model.entity.Sensor;
import com.bakulin.labinvent.test.task.model.entity.SensorType;
import com.bakulin.labinvent.test.task.model.entity.SensorUnit;
import com.bakulin.labinvent.test.task.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.bakulin.labinvent.test.task.constant.SensorConstant.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = {"/sensor"})
public class SensorController extends ExceptionHandling {
    private SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<SensorDTO>> getAllSensors() {
        List<SensorDTO> sensors = sensorService.getAllSensors();
        return new ResponseEntity<>(sensors, OK);
    }

    @GetMapping("/sensor-types")
    public ResponseEntity<List<SensorTypeDTO>> getAllSensorTypes() {
        List<SensorTypeDTO> sensorTypesDTOS = sensorService.findAllSensorTypes();
        return new ResponseEntity<>(sensorTypesDTOS, OK);
    }

    @GetMapping("/sensor-units")
    public ResponseEntity<List<SensorUnitDTO>> getAllSensorUnits() {
        List<SensorUnitDTO> sensorUnitsDTOS = sensorService.findAllSensorUnits();
        return new ResponseEntity<>(sensorUnitsDTOS, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Sensor> addNewSensor(
            @RequestParam("sensorName") String sensorName,
            @RequestParam("sensorModel") String sensorModel,
            @RequestParam("leftRange") String leftRange,
            @RequestParam("rightRange") String rightRange,
            @RequestParam("sensorType") String sensorTypeName,
            @RequestParam("sensorUnit") String sensorUnitName,
            @RequestParam("sensorLocation") String sensorLocation,
            @RequestParam("sensorDescription") String sensorDescription) throws SensorNotFoundException, SensorModelExistsException, SensorNameExistsException {
        SensorType sensorType = sensorService.findSensorTypeById(sensorTypeName);
        SensorUnit sensorUnit = sensorService.findSensorUnitById(sensorUnitName);

        Sensor newSensor = sensorService.addNewSensor(sensorName,sensorModel,Double.parseDouble(leftRange),Double.parseDouble(rightRange),sensorType, sensorUnit, sensorLocation, sensorDescription);
        return new ResponseEntity<>(newSensor, OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Sensor> updateSensor(
            @RequestParam("currentSensorName") String currentSensorName,
            @RequestParam("sensorName") String sensorName,
            @RequestParam("sensorModel") String sensorModel,
            @RequestParam("leftRange") String leftRange,
            @RequestParam("rightRange") String rightRange,
            @RequestParam("sensorType") String sensorTypeName,
            @RequestParam("sensorUnit") String sensorUnitName,
            @RequestParam("sensorLocation") String sensorLocation,
            @RequestParam("sensorDescription") String sensorDescription) throws SensorNotFoundException, SensorModelExistsException, SensorNameExistsException {
        SensorType sensorType = sensorService.findSensorTypeById(sensorTypeName);
        SensorUnit sensorUnit = sensorService.findSensorUnitById(sensorUnitName);

        Sensor updateSensor = sensorService.updateSensor(currentSensorName,sensorName,sensorModel,Double.parseDouble(leftRange),Double.parseDouble(rightRange),sensorType, sensorUnit, sensorLocation, sensorDescription);
        return new ResponseEntity<>(updateSensor, OK);
    }

    @DeleteMapping("/delete/{sensorName}")
    public ResponseEntity<HttpResponse> deleteSensor(@PathVariable("sensorName") String sensorName) {
         sensorService.deleteSensor(sensorName);
        return response(OK,SENSOR_DELETED_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }


}

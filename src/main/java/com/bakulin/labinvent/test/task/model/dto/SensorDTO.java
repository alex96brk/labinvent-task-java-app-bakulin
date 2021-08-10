package com.bakulin.labinvent.test.task.model.dto;

import com.bakulin.labinvent.test.task.model.entity.SensorType;
import com.bakulin.labinvent.test.task.model.entity.SensorUnit;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
public class SensorDTO {

    private String sensorId;

    private String sensorName;

    private String sensorModel;

    private Double leftRange;

    private Double rightRange;

    private String sensorType;

    private String sensorUnit;

    private String sensorLocation;

    private String sensorDescription;
}

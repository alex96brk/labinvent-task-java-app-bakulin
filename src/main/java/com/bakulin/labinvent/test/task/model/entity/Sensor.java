package com.bakulin.labinvent.test.task.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.value.qual.DoubleVal;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "sensors")
@AllArgsConstructor
@NoArgsConstructor
public class Sensor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sensor_id", unique = true)
    private String sensorId;

    @Column(name = "sensor_name", unique = true, nullable = false)
    private String sensorName;

    @Column(name = "sensor_model", unique = true, nullable = false)
    private String sensorModel;

    @Column(name = "left_range")
    private Double leftRange;

    @Column(name = "right_range")
    private Double rightRange;

    @ManyToOne
    @JoinColumn(name = "sensor_type")
    private SensorType sensorType;

    @ManyToOne
    @JoinColumn(name = "sensor_unit")
    private SensorUnit sensorUnit;

    @Column(name = "sensor_location")
    private String sensorLocation;

    @Column(name = "sensor_descr")
    private String sensorDescription;

}

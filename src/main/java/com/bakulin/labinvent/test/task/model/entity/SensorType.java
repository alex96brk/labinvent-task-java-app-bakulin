package com.bakulin.labinvent.test.task.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;



@Entity
@Table(name = "sensor_types")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SensorType implements Serializable {

    @Id
    @Column(name = "sensor_type_name")
    private String sensorTypeName;

    @OneToMany(mappedBy = "sensorType")
    @JsonIgnore
    private Set<Sensor> sensors;

}

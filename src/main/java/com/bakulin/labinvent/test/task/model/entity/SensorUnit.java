package com.bakulin.labinvent.test.task.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "sensor_units")
@AllArgsConstructor
@NoArgsConstructor
public class SensorUnit implements Serializable {

    @Id
    @Column(name = "sensor_unit_name")
    private String sensorUnitName;

    @OneToMany(mappedBy = "sensorUnit")
    @JsonIgnore
    private Set<Sensor> sensors;
}

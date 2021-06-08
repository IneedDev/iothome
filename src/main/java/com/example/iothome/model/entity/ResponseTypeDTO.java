package com.example.iothome.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sensor_data")
@Data
public class ResponseTypeDTO {

    //TODO Hibernate pooled and pooled-lo identifier generators https://vladmihalcea.com/hibernate-hidden-gem-the-pooled-lo-optimizer/

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 3
    )
    private int id;

    @Column(name = "sensor_id")
    private String sensor_id;

    @Column(name = "temperature")
    private double temperature = 0.0;

    @Column(name = "humidity")
    private double humidity = 0.0;

    @Column(insertable = true, updatable = false, name = "date_created")
    private String dateCreated;
}

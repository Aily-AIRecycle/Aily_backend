package aily.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(AvgDataId.class)
public class AvgDataEntity {

    @Id
    @Column(name = "number")
    private String id;
    @Id
    private String day;

    private String can;
    private String pet;
    private String gen;

}
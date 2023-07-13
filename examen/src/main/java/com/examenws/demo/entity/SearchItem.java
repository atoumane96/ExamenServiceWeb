package com.examenws.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SearchItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String request;
    @ManyToOne
    @JoinColumn(name = "day_id")
    private DayInfo dayInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private DayInfo response;
}

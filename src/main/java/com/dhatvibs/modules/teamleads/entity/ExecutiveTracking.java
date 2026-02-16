package com.dhatvibs.modules.teamleads.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "executive_tracking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecutiveTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long executiveId;

    private Integer target;
    private Integer achieved;
    private Integer balance;

    private String review;

    private LocalDate trackingDate;
}

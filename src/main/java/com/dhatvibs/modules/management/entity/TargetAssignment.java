package com.dhatvibs.modules.management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "target_assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teamLeadId;

    private Integer totalTarget;
}

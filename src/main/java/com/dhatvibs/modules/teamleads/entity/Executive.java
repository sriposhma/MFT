package com.dhatvibs.modules.teamleads.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "executives")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Executive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    private String employmentType; // FT / PT

    private Long teamLeadId; // From session USER_ID
}

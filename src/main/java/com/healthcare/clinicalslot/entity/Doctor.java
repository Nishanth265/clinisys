package com.healthcare.clinicalslot.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "doctor")
@Builder
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String specialization;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,unique = true)
    private User user;
}

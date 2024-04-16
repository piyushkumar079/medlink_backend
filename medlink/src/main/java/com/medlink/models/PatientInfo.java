package com.medlink.models;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.Data;

// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.OneToOne;

@Data
@NoArgsConstructor
@Entity
public class PatientInfo {

    @Id
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotNull(message = "Appointment date and time are required")
    private LocalDateTime dateTime;

    // @JsonIgnore
    // @OneToOne
    // private UserModel user;

    public PatientInfo(String name, String email, String phone, LocalDateTime dateTime) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dateTime = dateTime;
    }
}

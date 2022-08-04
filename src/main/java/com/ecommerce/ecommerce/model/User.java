package com.ecommerce.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity(name = "User")
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
    private static final long serialVerionUID = -423134453453445L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false,insertable = false,unique = true)
    private long id;

    @NotBlank
    @Column(name = "username", nullable = false, length = 255)
    private String username;

    @NotBlank
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;

    @NotBlank
    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;
}

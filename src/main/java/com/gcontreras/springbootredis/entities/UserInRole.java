package com.gcontreras.springbootredis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_in_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserInRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    public UserInRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}

package com.effective.shop.models.user;

import com.effective.shop.models.user.EUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "user_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {

    @Id
    @Column(updatable = false, nullable = false)
    private long id;

    @Enumerated(STRING)
    private EUserRole title;
}

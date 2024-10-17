package org.izouir.usermanagementservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "id", columnDefinition = "BIGSERIAL PRIMARY KEY")
    private Long id;

    @Column(name = "username", columnDefinition = "VARCHAR(16) UNIQUE NOT NULL")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(256) NOT NULL")
    private String password;

    @Column(name = "email", columnDefinition = "VARCHAR(256) UNIQUE NOT NULL")
    private String email;

    @Column(name = "phone", columnDefinition = "VARCHAR(16) UNIQUE NOT NULL")
    private String phone;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "USER_ROLE NOT NULL")
    private UserRole role;

    @Column(name = "name", columnDefinition = "VARCHAR(16) NOT NULL")
    private String name;

    @Column(name = "surname", columnDefinition = "VARCHAR(16) NOT NULL")
    private String surname;
}

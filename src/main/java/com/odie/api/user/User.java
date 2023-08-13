package com.odie.api.user;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// id uuid DEFAULT uuid_generate_v4 (),
// username VARCHAR(50) UNIQUE NOT NULL,
// password VARCHAR(50) NOT NULL,
// email VARCHAR(50) UNIQUE NOT NULL,
// active BOOLEAN,
// created_on TIMESTAMP NOT NULL,
// last_login TIMESTAMP NOT NULL
@Entity
@Table(name="USER", schema="public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=50, nullable = false, unique = true)
    private String username;

    @Column(length=50, nullable = false)    
    private String password;

    @Column(length=50, nullable = false, unique = true)
    private String email;

    private Boolean active;

    @Column(name="created_on", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate createdOn;

    @Column(name="last_login", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate lastLogin;


    
    public User(Long id, String username, String password, String email, Boolean active, LocalDate createdOn,
            LocalDate lastLogin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.active = active;
        this.createdOn = createdOn;
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", active="
                + active + ", createdOn=" + createdOn + ", lastLogin=" + lastLogin + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }
}

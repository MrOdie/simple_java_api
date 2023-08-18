package com.odie.api.user;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name="USER", schema="public")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=50, nullable = false, unique = true)
    private String username;

    @Column(length=50, nullable = false)    
    private String password;

    @Column(length=50, nullable = false, unique = true)
    private String email;

    private Boolean active;

    @CreationTimestamp
    @Column(name="created_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @LastModifiedDate
    @Column(name="last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    public User(String username, String email, Boolean active) {
        this.username = username;
        this.email = email;
        this.active = active;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", active="
                + active + ", createdOn=" + createdOn + ", lastLogin=" + lastModified + "]";
    }

}

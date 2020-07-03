package com.codegym.dao.model;

import com.codegym.dao.model.audit.DateAudit;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "support")
public class Support extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    private String title;
    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    private String email;
    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    private String phoneNumber;
    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    private String reason;
    @Column(columnDefinition = "TEXT NOT NULL")
    private String content;

    @Column(columnDefinition = "TINYINT(1) default 1")
    private boolean status;

    public Support() {
    }

    public Support(String title, String email, String phoneNumber, String reason, String content, boolean status) {
        this.title = title;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.reason = reason;
        this.content = content;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

package com.asaas.hackaton.domain.user;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String email;

    @Column(unique = true)
    String apiKey;

    @Column
    Integer maxPaymentsCreatedPerDay = 100;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public User setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public Integer getMaxPaymentsCreatedPerDay() {
        return maxPaymentsCreatedPerDay;
    }

    public void setMaxPaymentsCreatedPerDay(Integer maxPaymentsCreatedPerDay) {
        this.maxPaymentsCreatedPerDay = maxPaymentsCreatedPerDay;
    }
}

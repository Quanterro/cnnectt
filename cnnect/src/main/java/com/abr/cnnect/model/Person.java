package com.abr.cnnect.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {
    private final UUID id;
    private String name;
    private String lastName;
    private String email;

    public Person(@JsonProperty("name") String name,@JsonProperty("lastname") String lastName,@JsonProperty("email") String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }
    public Person(@JsonProperty("id") UUID id,@JsonProperty("name") String name,@JsonProperty("lastname") String lastName,@JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

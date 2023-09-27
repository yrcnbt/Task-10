package com.example.task10.model;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String name;

    private String lastName;

    private Byte age;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

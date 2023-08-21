package com.tester.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String passwd;

    public String toString() {
        return ("id" + ":" + id +
                "name" + ":" + name +
                "passwd" + ":" + passwd
        );
    }
}

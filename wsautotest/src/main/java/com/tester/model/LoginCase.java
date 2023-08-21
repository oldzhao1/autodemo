package com.tester.model;

import lombok.Data;

@Data
public class LoginCase {
    private int id;
    private String name;
    private String passwd;
    private String expected;
}

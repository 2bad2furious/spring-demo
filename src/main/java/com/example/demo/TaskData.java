package com.example.demo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonSerialize
public class TaskData {
    @NotNull @NotEmpty
    public String name;
}

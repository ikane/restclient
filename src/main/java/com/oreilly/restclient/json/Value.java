package com.oreilly.restclient.json;

import lombok.Data;

@Data
public class Value {
    private int id;
    private String joke;
    private String[] categories;
}

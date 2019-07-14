package com.oreilly.restclient.json;

import lombok.Data;

@Data
public class JokeResponse {
    private String type;
    private Value value;
}

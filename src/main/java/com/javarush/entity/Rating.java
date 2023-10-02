package com.javarush.entity;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String value;

    Rating(String realName) {
        this.value = realName;
    }
}

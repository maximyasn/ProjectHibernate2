package com.javarush.converters;

import jakarta.persistence.AttributeConverter;

public class NumBoolConverter implements AttributeConverter<Boolean, Byte> {
    @Override
    public Byte convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean? (byte)1 : (byte)0;
    }

    @Override
    public Boolean convertToEntityAttribute(Byte aByte) {
        return aByte == 1;
    }
}

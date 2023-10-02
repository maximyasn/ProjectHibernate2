package com.javarush.converters;

import com.javarush.entity.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        for(Rating rating : Rating.values()) {
            if(rating.getValue().equals(s)) {
                return rating;
            }
        }
        return null;
    }
}

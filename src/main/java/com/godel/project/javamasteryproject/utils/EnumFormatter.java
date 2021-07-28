package com.godel.project.javamasteryproject.utils;

import com.godel.project.javamasteryproject.enums.Gender;
import org.springframework.core.convert.converter.Converter;


public class EnumFormatter implements Converter<String, Gender> {
    @Override
    public Gender convert(String s) {
        try {
            return Gender.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("not exist such a gender : (%s)", s));
        }
    }
}

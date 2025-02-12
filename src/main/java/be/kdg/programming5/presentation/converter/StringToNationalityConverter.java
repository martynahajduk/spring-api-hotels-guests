package be.kdg.programming5.presentation.converter;


import be.kdg.programming5.domain.Nationality;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToNationalityConverter implements Converter<String, Nationality> {

    @Override
    public Nationality convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        try {
            return Nationality.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid nationality: " + source);
        }
    }
}


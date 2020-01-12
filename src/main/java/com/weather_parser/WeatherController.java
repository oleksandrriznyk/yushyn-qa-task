package com.weather_parser;

import com.weather_parser.exception.UnsupportedWeatherFormatException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class WeatherController {

    private static final Pattern VALID_WEATHER_PATTERN = Pattern.compile("([+\\-])(\\d{1,2})C");
    private static final Map<Predicate<Integer>, String> WEATHER_ADVICES = new HashMap<>();

    static {
        WEATHER_ADVICES.put((value) -> value >= -50 && value <= -1, "It’s super cold today. Be sure you dressed well!");
        WEATHER_ADVICES.put((value) -> value >= 0 && value <= 10, "It’s windy outside, but we are sure you will enjoy your day!");
        WEATHER_ADVICES.put((value) -> value >= 11 && value <= 30, "It’s time for the outdoor walking!");
    }

    public String checkWeather(String todayWeather) {
        if (isNotValidWeather(todayWeather)) {
            throw new UnsupportedWeatherFormatException("Weather is not a valid string.");
        }

        int celsius = parseIntegerPartOfWeatherString(todayWeather);

        return getWeatherAdvice(celsius);
    }

    private String getWeatherAdvice(int celsius) {
        return WEATHER_ADVICES.entrySet().stream()
                .filter(predicateStringEntry -> predicateStringEntry.getKey().test(celsius))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse("Please re-check results in 5 mins.");
    }

    private boolean isNotValidWeather(String todayWeather) {
        return !VALID_WEATHER_PATTERN.matcher(todayWeather).matches();
    }

    private Integer parseIntegerPartOfWeatherString(String todayWeather) {
        return Integer.parseInt(todayWeather.replace("C", ""));
    }

}

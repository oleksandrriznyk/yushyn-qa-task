package com.weather_parser;

import com.weather_parser.exception.UnsupportedWeatherFormatException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class WeatherControllerTest {

    private WeatherController subject = new WeatherController();

    @Test
    public void checkWeather_whenCelsiusLessThan50BelowZero() {
        String actual = subject.checkWeather("-51C");

        assertThat(actual).isEqualTo("Please re-check results in 5 mins.");
    }

    @Test
    public void checkWeather_whenCelsiusBetween50BelowZeroTo1BelowZero() {
        String actual = subject.checkWeather("-45C");

        assertThat(actual).isEqualTo("It’s super cold today. Be sure you dressed well!");
    }

    @Test
    public void checkWeather_whenCelsiusBetween0To10AboveZero() {
        String actual = subject.checkWeather("+5C");

        assertThat(actual).isEqualTo("It’s windy outside, but we are sure you will enjoy your day!");
    }

    @Test
    public void checkWeather_whenCelsiusGreaterThan10AboveZeroAndLessThan30AboveZero() {
        String actual = subject.checkWeather("+22C");

        assertThat(actual).isEqualTo("It’s time for the outdoor walking!");
    }

    @Test
    public void checkWeather_whenCelsiusLessGreaterThan30AboveZero() {
        String actual = subject.checkWeather("+31C");

        assertThat(actual).isEqualTo("Please re-check results in 5 mins.");
    }

    @Test
    public void checkWeather_whenInputIsInvalid_throwsException() {
        assertThatThrownBy(() -> subject.checkWeather("31"))
                .isInstanceOf(UnsupportedWeatherFormatException.class)
                .hasMessage("Weather is not a valid string.");
    }

}
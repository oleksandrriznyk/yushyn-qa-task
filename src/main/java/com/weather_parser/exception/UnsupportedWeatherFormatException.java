package com.weather_parser.exception;

public class UnsupportedWeatherFormatException extends RuntimeException {
    public UnsupportedWeatherFormatException(String message) {
        super(message);
    }
}

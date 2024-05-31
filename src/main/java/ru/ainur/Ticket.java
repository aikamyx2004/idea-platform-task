package ru.ainur;


import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Ticket(
        @SerializedName("origin") String origin,
        @SerializedName("origin_name") String originName,
        @SerializedName("destination") String destination,
        @SerializedName("destination_name") String destinationName,
        @SerializedName("departure_date") String departureDate,
        @SerializedName("departure_time") String departureTime,
        @SerializedName("arrival_date") String arrivalDate,
        @SerializedName("arrival_time") String arrivalTime,
        @SerializedName("carrier") String carrier,
        @SerializedName("stops") int stops,
        @SerializedName("price") int price
) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("[dd.MM.yy H:mm][dd.MM.yy HH:mm]");

    private LocalDateTime parseDateTime(String date, String time) {
        return LocalDateTime.parse(date + " " + time, FORMATTER);
    }

    public LocalDateTime departureDateTime() {
        return parseDateTime(departureDate, departureTime);
    }

    public LocalDateTime arrivalDateTime() {
        return parseDateTime(arrivalDate, arrivalTime);
    }

}
package ru.ainur;

import java.time.Duration;

public class TicketUtil {

    public static int compareByTime(Ticket ticket1, Ticket ticket2) {
        Duration flightTime1 = getFlightDuration(ticket1);
        Duration flightTime2 = getFlightDuration(ticket2);

        return flightTime1.compareTo(flightTime2);
    }

    public static Duration getFlightDuration(Ticket ticket) {
        return Duration.between(ticket.departureDateTime(), ticket.arrivalDateTime());
    }

    public static String getFlightDurationString(Ticket ticket) {
        Duration duration = getFlightDuration(ticket);

        long HH = duration.toHours();
        long MM = duration.toMinutesPart();
        long SS = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", HH, MM, SS);
    }

    public static boolean isBetweenVVOAndTLV(Ticket ticket) {
        return "VVO".equals(ticket.origin()) && "TLV".equals(ticket.destination());
    }
}

package ru.ainur;

import com.google.gson.Gson;

import java.util.*;

public class TicketAnalyzer {
    private final Tickets allTickets;

    public TicketAnalyzer(String inputStream) {
        this.allTickets = new Gson().fromJson(inputStream, Tickets.class);
    }

    public void analyze() {
        List<Ticket> tickets = new ArrayList<>(allTickets.tickets().stream()
                .filter(TicketUtil::isBetweenVVOAndTLV)
                .toList());
        analyzeMinimalTimeForCarrier(tickets);

        analyzeAverageAndMedianPrice(tickets);
    }

    private void analyzeMinimalTimeForCarrier(List<Ticket> tickets) {
        Map<String, Ticket> carrierToShortestTicket = new HashMap<>();

        tickets.forEach(t -> {
            Ticket shortestTicket = carrierToShortestTicket.getOrDefault(t.carrier(), t);

            if (TicketUtil.compareByTime(t, shortestTicket) <= 0) {
                carrierToShortestTicket.put(t.carrier(), t);
            }
        });

        carrierToShortestTicket.forEach((carrier, ticket) ->
                System.out.printf(
                        "Minimal time for '%s' carrier of flight between Vladivostok and Tel Aviv is %s%n",
                        carrier, TicketUtil.getFlightDurationString(ticket)
                )
        );
        System.out.println();
    }


    private void analyzeAverageAndMedianPrice(List<Ticket> tickets) {
        double average = tickets.stream().mapToDouble(Ticket::price).average().orElseThrow();
        System.out.printf("Average price is: %f%n", average);


        tickets.sort(Comparator.comparingInt(Ticket::price));
        double medianPrice;
        if (tickets.size() % 2 == 1) {
            medianPrice = tickets.get(tickets.size() / 2).price();
        } else {
            int a = tickets.get(tickets.size() / 2 - 1).price();
            int b = tickets.get(tickets.size() / 2).price();
            medianPrice = (a + b) / 2.0;
        }
        System.out.printf("Median price is: %f%n", medianPrice);
        double diff = medianPrice - average;
        if (diff > 0) {
            System.out.printf("Difference between median and average price is: %f", diff);
        } else {
            System.out.printf("Difference between average and median price is: %f", -diff);
        }
    }
}

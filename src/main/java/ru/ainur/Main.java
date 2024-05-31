package ru.ainur;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final String PATH = "/tickets.json";

    public static void main(String[] args) {
        try (InputStream inputStream = Main.class.getResourceAsStream(PATH)) {
            if (inputStream != null) {
                String data = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                new TicketAnalyzer(data).analyze();
            }
            return;
        } catch (IOException e) {
            //no operations, message it will be printed below
        }
        System.err.printf("Could not load %s file%n", PATH);
    }
}

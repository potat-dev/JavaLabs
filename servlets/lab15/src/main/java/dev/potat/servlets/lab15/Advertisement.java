package dev.potat.servlets.lab15;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {
    private String title;
    private String image;
    private String desc;
    private String user;
    private String date;

    public Advertisement(String title, String image, String desc, String user) {
        this(title, image, desc, user, getNow());
    }

    private static String getNow() {
        // get current date-time, with system default time zone
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss");
        ZonedDateTime now = ZonedDateTime.now();
        // get current date-time, with a specified time zone
        ZonedDateTime japanDateTime = now.withZoneSameInstant(ZoneId.of("Europe/Moscow"));
        return dtf.format(japanDateTime);
    }
}

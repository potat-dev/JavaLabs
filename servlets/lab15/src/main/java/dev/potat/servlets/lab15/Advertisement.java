package dev.potat.servlets.lab15;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
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
    private String id;
    private String title;
    private String image;
    private String desc;
    private String user;
    private String date;
    private int score;

    public Advertisement(String title, String image, String desc, String user) {
        this(NanoIdUtils.randomNanoId(), title, image, desc, user, getNow(), 0);
    }

    private static String getNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss");
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime japanDateTime = now.withZoneSameInstant(ZoneId.of("Europe/Moscow"));
        return dtf.format(japanDateTime);
    }

    public void vote(String action) {
        if (action.equals("like")) {
            score++;
        } else if (action.equals("dislike")) {
            score--;
        }
    }
}

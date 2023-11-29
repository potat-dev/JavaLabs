package dev.potat.servlets.lab15;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

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
    private HashMap<String, Vote> votes;


    public Advertisement(String title, String image, String desc, String user) {
        this(NanoIdUtils.randomNanoId(), title, image, desc, user, getNow(), 0, new HashMap<>());
    }

    private static String getNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime japanDateTime = now.withZoneSameInstant(ZoneId.of("Europe/Moscow"));
        return dtf.format(japanDateTime);
    }

    public void vote(String userId, String action) {
        switch (action) {
            case "like":
                votes.put(userId, Vote.LIKE);
                break;
            case "dislike":
                votes.put(userId, Vote.DISLIKE);
                break;
            case "remove":
                votes.remove(userId);
                break;
        }
        score = calcScore();
    }

    public Vote getVoteBy(String user) {
        return votes.getOrDefault(user, Vote.NONE);
    }

    public boolean likedBy(String user) {
        return getVoteBy(user) == Vote.LIKE;
    }

    public boolean dislikedBy(String user) {
        return getVoteBy(user) == Vote.DISLIKE;
    }

    private int calcScore() {
        int score = 0;
        for (Vote vote : votes.values()) {
            score += (vote == Vote.LIKE ? 1 : -1);
        }
        return score;
    }

    public enum Vote {
        NONE, LIKE, DISLIKE
    }
}

package dev.potat.servlets.lab15;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Advertisement {
    private String title;
    private String body;
    private String date;
    private String user;
    private String image;
}

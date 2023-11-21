package dev.potat.servlets.lab15;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class AdBoard {
    private ArrayList<Advertisement> ads = new ArrayList<>();

    public void add(Advertisement ad) {
        ads.add(ad);
    }
}

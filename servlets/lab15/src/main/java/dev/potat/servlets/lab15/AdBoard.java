package dev.potat.servlets.lab15;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;

@Data
@NoArgsConstructor
public class AdBoard {
    private ArrayList<Advertisement> ads = new ArrayList<>();

    public void add(Advertisement ad) {
        ads.add(ad);
    }

    public Advertisement getById(String id) {
        for (Advertisement ad : ads) {
            if (ad.getId() != null && ad.getId().equals(id)) {
                return ad;
            }
        }
        return null;
    }

    public void sort() {
        ads.sort(Comparator.comparing(Advertisement::getScore).reversed());
    }
}

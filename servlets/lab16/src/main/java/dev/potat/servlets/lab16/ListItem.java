package dev.potat.servlets.lab16;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListItem {
    private String name;
    private List<String> items = new ArrayList<>();
}

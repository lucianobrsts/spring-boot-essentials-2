package academy.devdojo.springboot2.domain;

import lombok.Data;

@Data
public class Anime {
    private String name;

    public Anime(String name) {
        this.name = name;
    }

    public Anime() {
    }
}

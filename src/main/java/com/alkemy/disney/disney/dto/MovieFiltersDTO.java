package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MovieFiltersDTO {
    private String name;
    private String genre;
    private String order;

    public MovieFiltersDTO(String name, String genre, String order) {
        this.name = name;
        this.genre = genre;
        this.order = order;
    }

    public boolean isASC() {return this.order.compareToIgnoreCase("ASC") == 0;}
    public boolean isdDESC() {return this.order.compareToIgnoreCase("DESC") == 0;}
}

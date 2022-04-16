package com.dh.serie.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Chapter {

    private Integer id;
    private String name;
    private Integer number;
    private String urlStream;

}

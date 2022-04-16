package com.dh.catalogservice.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SerieWS {

    private String id;
    private String name;
    private String genre;
    private List<SeasonsWS> seasons;

}

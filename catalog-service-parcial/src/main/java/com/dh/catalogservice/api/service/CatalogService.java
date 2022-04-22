package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.Catalog;
import com.dh.catalogservice.domain.model.dto.*;
import com.dh.catalogservice.domain.repository.catalogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CatalogService {

    private catalogRepository catalogRepository;
    private MovieService movieService;
    private SerieService serieService;

    public CatalogService(catalogRepository catalogRepository, MovieService movieService, SerieService serieService) {
        this.catalogRepository = catalogRepository;
        this.movieService = movieService;
        this.serieService = serieService;
    }

    public Catalog getCatalogFromGenre(String genre){

        List<MovieWS> movieWSList = movieService.getAllMoviesByGenre(genre);
        List<SerieWS> serieWSList = serieService.getAllMoviesByGenre(genre);

        Catalog res = new Catalog(genre,movieWSList, serieWSList);
        Catalog r = catalogRepository.findByGenre(genre).orElse(null);
        if(r != null){
            catalogRepository.deleteByGenre(genre);
        }
        catalogRepository.save(res);

        return res;
    }



    public Catalog saveMovieOnCatalog(MovieWS movieWS){


        Catalog r = catalogRepository.findByGenre(movieWS.getGenre()).orElse(null);
        if(r == null){
            Catalog c = new Catalog(movieWS.getGenre(), new ArrayList<>(), new ArrayList<>() );
            c.getMoviesws().add(movieWS);
            catalogRepository.save(c);
        }

        r.getMoviesws().add(movieWS);
        return catalogRepository.save(r);
    }

    public Catalog saveSerieOnCatalog(SerieWS serieWS){

        Catalog r = catalogRepository.findByGenre(serieWS.getGenre()).orElse(null);
        if(r == null){
            Catalog c = new Catalog(serieWS.getGenre(), new ArrayList<>(), new ArrayList<>() );
            c.getSerieWS().add(serieWS);
            catalogRepository.save(c);
        }

        r.getSerieWS().add(serieWS);
        return catalogRepository.save(r);
    }


}

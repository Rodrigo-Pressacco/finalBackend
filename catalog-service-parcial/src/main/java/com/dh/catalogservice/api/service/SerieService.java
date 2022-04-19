package com.dh.catalogservice.api.service;


import com.dh.catalogservice.domain.model.dto.SerieWS;
import com.dh.catalogservice.domain.repository.serieFeignRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SerieService {

    private serieFeignRepository serieFeignRepository;

    public SerieService(serieFeignRepository serieFeignRepository) {
        this.serieFeignRepository = serieFeignRepository;
    }

    public List<SerieWS> getAllMovies(){
        return serieFeignRepository.getSerie();
    }

    @CircuitBreaker(name = "catalogFromGenre",fallbackMethod = "serieFallbackMethod")
    @Retry(name = "catalogFromGenre")
    public List<SerieWS> getAllMoviesByGenre(String genre){
        return serieFeignRepository.getSerieByGenre(genre);
    }

    public SerieWS saveMovie(SerieWS serie){
        return serieFeignRepository.saveSerie(serie);
    }

    private List<SerieWS> serieFallbackMethod(Throwable t){
        log.error("Circuit breaker was activated: {}", t.getMessage());

        return new ArrayList<>();
    }
}

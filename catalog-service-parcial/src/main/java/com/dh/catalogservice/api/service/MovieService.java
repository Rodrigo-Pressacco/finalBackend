package com.dh.catalogservice.api.service;


import com.dh.catalogservice.domain.model.dto.MovieWS;
import com.dh.catalogservice.domain.repository.movieFeignRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MovieService {

    private movieFeignRepository movieFeignRepository;

    @Autowired
    public MovieService(com.dh.catalogservice.domain.repository.movieFeignRepository movieFeignRepository) {
        this.movieFeignRepository = movieFeignRepository;
    }

    public List<MovieWS> getAllMovies(){
        return movieFeignRepository.getMovie();
    }

    @CircuitBreaker(name = "movie",fallbackMethod = "moviesFallbackMethod")
    @Retry(name = "movie")
    public List<MovieWS> getAllMoviesByGenre(String genre){
        return movieFeignRepository.getMovieByGenre(genre);
    }

    public MovieWS saveMovie(MovieWS movie){
        return movieFeignRepository.saveMovie(movie);
    }

    public List<MovieWS> moviesFallbackMethod(Throwable t){
        log.error("Circuit breaker was activated: {}", t.getMessage());
        return new ArrayList<>();
    }

}

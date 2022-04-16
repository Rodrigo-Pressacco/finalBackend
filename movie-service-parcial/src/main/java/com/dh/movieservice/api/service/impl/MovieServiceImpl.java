package com.dh.movieservice.api.service.impl;

import com.dh.movieservice.domain.model.Movie;
import com.dh.movieservice.domain.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl{

	private MovieRepository movieRepository;

	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	public Movie saveMovie(Movie m){
		return movieRepository.save(m);
	}


	public List<Movie> findAllByGenre(String genero) {
		return movieRepository.findAllByGenre(genero);
	}


	public void deleteByName(String name){
		movieRepository.deleteMovieByName(name);
	}





}

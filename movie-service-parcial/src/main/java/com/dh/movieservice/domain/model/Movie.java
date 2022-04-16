package com.dh.movieservice.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Movie {

	@Id
	private String id;
	private String name;
	private String genre;
	private String urlStream;

	public Movie(String name, String genre, String urlStream) {
		this.name = name;
		this.genre = genre;
		this.urlStream = urlStream;
	}
}

package com.dh.catalogservice.api.service.queue;


import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.dto.MovieWS;
import com.dh.catalogservice.domain.repository.catalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovieListener {

    private final CatalogService catalogService;

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receive(@Payload MovieWS movie){
        // crear un metodo para guardar
        log.info("Se guardo una pelicula en el catologo de "+ movie.getGenre());
        catalogService.saveMovieOnCatalog(movie);
    }
}

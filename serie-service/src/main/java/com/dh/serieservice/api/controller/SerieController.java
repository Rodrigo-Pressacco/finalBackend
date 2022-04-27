package com.dh.serieservice.api.controller;

import com.dh.serieservice.api.queue.SerieSender;
import com.dh.serieservice.api.service.SerieService;
import com.dh.serieservice.domain.model.Serie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("")
public class SerieController {

    private final SerieService serieService;
    public final SerieSender serieSender;

    @Autowired
    public SerieController(SerieService serieService, SerieSender serieSender) {
        this.serieService = serieService;
        this.serieSender = serieSender;
    }

    @GetMapping()
    public ResponseEntity<?> findAllSerie(){
        log.info("Trayendo todas las series");
        return ResponseEntity.ok().body(serieService.getAllSerie());
    }

    @GetMapping("{genre}")
    public ResponseEntity<?> findAllSerieByGenre(@PathVariable String genre){
        log.info("Trayendo las series con el genero "+ genre);
        return ResponseEntity.ok().body(serieService.findAllByGenre(genre));
    }

    @PostMapping("save")
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        log.info("Guardando la serie "+ serie);
        var resultado = serieService.saveSerie(serie);
        serieSender.send(resultado);
        return ResponseEntity.ok().body(resultado);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable String name) {
        log.info("Trayendo todas las serie");
        serieService.deleteByName(name);
        return ResponseEntity.ok().body("Se borro su serie");
    }


}

package ru.otus.HW111.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Genre;
import ru.otus.HW111.service.GenreService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class GenreHandler {

    private GenreService genreService;

    public GenreHandler(GenreService genreService) {
        this.genreService = genreService;
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(genreService.findAll(), Genre.class);
    }

    public Mono<ServerResponse> createGenre(ServerRequest request) {
        Mono<Genre> genre = request.bodyToMono(Genre.class);
        return genre.doOnNext(x -> genreService.save(x).subscribe()).
                then(ok().build());
    }
}

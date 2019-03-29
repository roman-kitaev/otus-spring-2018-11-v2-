package ru.otus.HW111.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Author;
import ru.otus.HW111.service.AuthorService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class AuthorHandler {
    private AuthorService authorService;

    public AuthorHandler(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(authorService.findAll(), Author.class);
    }

    public Mono<ServerResponse> createAuthor(ServerRequest request) {
        Mono<Author> author = request.bodyToMono(Author.class);
        return author.doOnNext(x -> authorService.save(x).subscribe()).
                then(ok().build());
    }
}

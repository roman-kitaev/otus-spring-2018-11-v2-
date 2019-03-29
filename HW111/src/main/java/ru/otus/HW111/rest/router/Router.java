package ru.otus.HW111.rest.router;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.HW111.rest.AuthorHandler;
import ru.otus.HW111.rest.BookHandler;
import ru.otus.HW111.rest.GenreHandler;
import ru.otus.HW111.service.AuthorService;
import ru.otus.HW111.service.BookService;
import ru.otus.HW111.service.GenreService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Router {
    @Autowired
    AuthorHandler authorHandler;

    @Autowired
    GenreHandler genreHandler;

    @Autowired
    BookHandler bookHandler;

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(AuthorService authorService,
                                                         GenreService genreService, BookService bookService) {

        RouterFunction<ServerResponse> route = route()
                .GET("/func/authors", accept(APPLICATION_JSON), authorHandler::list)
                .POST("/func/authors/create", accept(APPLICATION_JSON), authorHandler::createAuthor)

                .GET("/func/genres", accept(APPLICATION_JSON), genreHandler::list)
                .POST("/func/genres/create", accept(APPLICATION_JSON), genreHandler::createGenre)

                .GET("/func/books", accept(APPLICATION_JSON), bookHandler::list)
                .POST("/func/books/create", accept(APPLICATION_JSON), bookHandler::createBook)
                .GET("/func/books/title", accept(APPLICATION_JSON), bookHandler::findByTitle)
                .GET("/func/books/{id}", accept(APPLICATION_JSON), bookHandler::findById)
                .POST("/func/books/{id}/addcomment", accept(TEXT_PLAIN), bookHandler::addComment)
                .DELETE("/func/books/deletebyid", accept(APPLICATION_JSON), bookHandler::deleteById)
                .build();

        return route;
    }
}

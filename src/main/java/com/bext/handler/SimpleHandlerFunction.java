package com.bext.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class SimpleHandlerFunction {

    public Mono<ServerResponse> flux(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body( Flux.just(1,2,3,4).log(), Integer.class);
    }

    public Mono<ServerResponse> fluxString(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body( Flux.just("Elemento1","Elemento2","Elemento3","Elemento4").log(), String.class);
    }

    public Mono<ServerResponse> mono(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body( Mono.just(1).log(), Integer.class);
    }

    public Mono<ServerResponse> monoString(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body( Mono.just("Unico").log(), String.class);
    }

}

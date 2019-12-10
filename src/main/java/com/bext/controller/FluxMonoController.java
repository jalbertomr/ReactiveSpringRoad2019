package com.bext.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class FluxMonoController {
    @GetMapping("/flux")
    public Flux<Integer> flux() {
        return Flux.just(1,2,3,4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping("/fluxString")
    public Flux<String> fluxString() {
       return Flux.just("Elemento1","Elemento2","Elemento3","Elemento4").log();
    }

    @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> fluxStream() {
        return Flux.interval(Duration.ofSeconds(1))  //generate infinite succesive values
                .log();
    }

    @GetMapping("/mono")
    public Mono<Integer> mono(){
        return Mono.just(1)
                .log();
    }
}

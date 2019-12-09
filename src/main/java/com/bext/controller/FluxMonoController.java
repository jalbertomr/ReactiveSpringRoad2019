package com.bext.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class FluxMonoController {
    @GetMapping("/flux")
    Flux<Integer> flux() {
        return Flux.just(1,2,3,4)
                .log();
    }
}

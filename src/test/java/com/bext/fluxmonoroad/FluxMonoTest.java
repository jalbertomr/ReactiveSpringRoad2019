package com.bext.fluxmonoroad;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class FluxMonoTest {

    @Test
    void fluxTest() {
        Flux<String> fluxString = Flux.just("Elemento1", "Elemento2", "Elemento3");
        fluxString.subscribe(System.out::println);
    }
}
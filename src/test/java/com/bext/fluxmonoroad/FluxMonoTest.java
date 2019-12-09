package com.bext.fluxmonoroad;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class FluxMonoTest {

    @Test
    void fluxTest() {
        Flux<String> fluxString = Flux.just("Elemento1", "Elemento2", "Elemento3")
                .concatWith( Flux.error( new RuntimeException("Exception generated voluntarily")));
        fluxString.subscribe(System.out::println, ex -> System.err.println(ex));
    }
}
package com.bext.fluxmonoroad;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class FluxMonoTest {

    @Test
    void fluxTest() {
        Flux<String> fluxString = Flux.just("Elemento1", "Elemento2", "Elemento3")
                .concatWith( Flux.just("Esto se Mostrara"))
                .concatWith( Flux.error( new RuntimeException("Exception generated voluntarily")))
                .concatWith( Flux.just("Esto NO se Mostara"))
                .log();
        fluxString.subscribe(System.out::println,
                             ex -> System.err.println("El error es :" + ex),
                             () -> System.out.println("Completado"));
    }
}
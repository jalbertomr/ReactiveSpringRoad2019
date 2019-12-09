package com.bext.fluxmonoroad;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxMonoTest {

    @Test
    void fluxTest() {
        Flux<String> fluxString = Flux.just("Elemento1", "Elemento2", "Elemento3")
                .concatWith( Flux.just("Esto se Mostrara"))
                //.concatWith( Flux.error( new RuntimeException("Exception generated voluntarily")))
                .concatWith( Flux.just("Esto NO se Mostara, Se muestra ya no hay exception"))
                .log();
        fluxString.subscribe(System.out::println,
                             ex -> System.err.println("El error es :" + ex),
                             () -> System.out.println("Completado"));
    }

    @Test
    void fluxElements_WithoutError(){
        Flux<String> fluxString = Flux.just("Elemento1","Elemento2","Elemento3")
                .log();
        StepVerifier.create( fluxString)
                .expectNext("Elemento1")
                .expectNext("Elemento2")
                .expectNext("Elemento3")
                .verifyComplete();
    }

    @Test
    void fluxElements_WithError(){
        Flux<String> fluxString = Flux.just("Elemento1","Elemento2","Elemento3")
                .concatWith( Flux.error( new RuntimeException("Exception generated voluntarily")))
                .log();
        StepVerifier.create( fluxString)
                .expectNext("Elemento1")
                .expectNext("Elemento2")
                .expectNext("Elemento3")
                .verifyComplete();
    }
}
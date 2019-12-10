package com.bext.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebFluxTest
class FluxMonoControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void flux_webClient01() {
        Flux<Integer> fluxInteger = webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create( fluxInteger)
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .verifyComplete();
    }

    @Test
    public void flux_webClient02() {
         webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Integer.class)
                .hasSize(4);
    }

    @Test
    public void flux_webClient03() {
        List<Integer> expectedListInteger = Arrays.asList(1,2,3,4);

        EntityExchangeResult<List<Integer>> entityExchangeResult =
        webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .returnResult();

        assertEquals( expectedListInteger, entityExchangeResult.getResponseBody());
    }

    @Test
    public void flux_webClient04() {
        List<Integer> expectedListInteger = Arrays.asList(1,2,3,4);

        webTestClient.get().uri("/flux")
                        .accept(MediaType.APPLICATION_JSON)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(Integer.class)
                        .consumeWith( response ->
                                assertEquals( expectedListInteger, response.getResponseBody()));

    }

    @Test
    public void fluxString_webClient01() {
        Flux<String> fluxString = webTestClient.get().uri("/fluxString")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class)
                .getResponseBody();

        StepVerifier.create( fluxString)
                .expectSubscription()
                .expectNext("Elemento1Elemento2Elemento3Elemento4")
                .verifyComplete();
    }

    @Test
    void fluxStream() {
        Flux<Long> fluxLong = webTestClient.get().uri("/fluxstream")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();

        StepVerifier.create(fluxLong)
                .expectSubscription()
                .expectNext(0L)
                .expectNext(1L)
                .expectNext(2L)
                .thenCancel()
                .verify();
    }

    @Test
    void mono_WebClient01(){
        Integer expectedValue = new Integer(1);

        EntityExchangeResult<Integer> entityExchangeResult = webTestClient.get().uri("/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .returnResult();

        assertEquals( expectedValue, entityExchangeResult.getResponseBody());
    }

    @Test
    void mono_WebClient02(){
        Integer expectedValue = new Integer(1);

        webTestClient.get().uri("/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith( response -> {
                        assertEquals( expectedValue, response.getResponseBody());
                });
    }
}
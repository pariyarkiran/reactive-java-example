package com.kiranpariyar.reactive.java.example.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoService {


    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Mango","Apple","Grape")).log();
    }


    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Mango","Apple","Grape"))
                .map(String::toUpperCase)
                .log();
    }


    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("Mango","Apple","Grape", "Orange", "Banana"))
                .filter(s -> s.length() > number)
                .log();
    }


    public Flux<String> fruitsFluxFilterAndMap(int number) {
        return Flux.fromIterable(List.of("Mango","Apple","Grape", "Orange", "Banana"))
                .filter(s -> s.length() > number)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Mango","Apple","Grape"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }


    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Mango","Apple","Grape"))
                .flatMap(s -> Flux.just(s.split(""))
                        .delayElements(Duration.ofMillis(new Random().nextInt(1000))
                        ))
                .log();
    }


    public Mono<String> fruitMono() {
        return Mono.just("Mango");
    }

    public static void main(String[] args) {
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        fluxAndMonoService.fruitsFlux()
                .subscribe(s-> {
                    System.out.println("Data - " + s);
                });

        fluxAndMonoService.fruitMono()
                .subscribe(s -> {
                    System.out.println("Mono - " + s);
                });

    }
}

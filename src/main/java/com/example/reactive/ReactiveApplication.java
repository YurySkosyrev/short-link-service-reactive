package com.example.reactive;

import com.example.reactive.links.LinksService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class ReactiveApplication {

	private final LinksService linksService;

	public ReactiveApplication(LinksService linksService) {
		this.linksService = linksService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@GetMapping
	public Mono<Integer> code(){
		return linksService.randomPull().then(Mono.just(Thread.activeCount()));
	}
}

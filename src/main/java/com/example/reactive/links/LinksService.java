package com.example.reactive.links;


import com.example.reactive.common.ShortLink;
import reactor.core.publisher.Mono;

public interface LinksService {

    void save(ShortLink link);

    void remove(String key);

    Mono<ShortLink> get(Mono<String> code);

    Mono<String> randomKey();

    Mono<ShortLink> randomPull();

}

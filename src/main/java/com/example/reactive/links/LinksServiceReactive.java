package com.example.reactive.links;

import com.example.reactive.common.ShortLink;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import reactor.core.publisher.Mono;

public class LinksServiceReactive implements LinksService{

    ReactiveRedisOperations<String, ShortLink> operations;

    public LinksServiceReactive(ReactiveRedisOperations<String, ShortLink> operations) {
        this.operations = operations;
    }

    @Override
    public void save(ShortLink link) {
        operations.opsForValue().set(link.getCode(), link);
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public Mono<ShortLink> get(Mono<String> code) {
        return null;
    }

    @Override
    public Mono<String> randomKey() {
        return null;
    }

    @Override
    public Mono<ShortLink> randomPull() {
        return null;
    }
}

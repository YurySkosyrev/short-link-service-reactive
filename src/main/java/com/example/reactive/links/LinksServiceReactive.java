package com.example.reactive.links;

import com.example.reactive.common.ShortLink;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class LinksServiceReactive implements LinksService{

    private final Long SAVE_TIMEOUT_MS = 5000L;

    ReactiveRedisOperations<String, ShortLink> operations;

    public LinksServiceReactive(ReactiveRedisOperations<String, ShortLink> operations) {
        this.operations = operations;
    }

    @Override
    public void save(ShortLink link) {
        operations.opsForValue().set(link.getCode(), link, Duration.ofMillis(SAVE_TIMEOUT_MS)).subscribe();
    }

    @Override
    public void remove(String key) {
        operations.delete(key).subscribe();
    }

    @Override
    public Mono<ShortLink> get(Mono<String> code) {
        return code.flatMap(operations.opsForValue()::get);
    }

    @Override
    public Mono<String> randomKey() {
        return operations.randomKey();
    }

    @Override
    public Mono<ShortLink> randomPull() {
        return get(randomKey());
    }
}

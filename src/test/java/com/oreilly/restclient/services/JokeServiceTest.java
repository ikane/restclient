package com.oreilly.restclient.services;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JokeServiceTest {

    @Autowired
    private JokeService jokeService;

    @Test
    public void getJokeSync() {
        String jokeSync = jokeService.getJokeSync("Craig", "Walls");
        log.info("Joke:: {}", jokeSync);
        Assertions.assertThat(jokeSync).isNotEmpty();
    }

    @Test
    public void getJokeASync() {
        String jokeAsync = jokeService.getJokeAsync("Craig", "Walls")
                .block(Duration.ofSeconds(2));
        log.info("Joke:: {}", jokeAsync);
        Assertions.assertThat(jokeAsync).isNotEmpty();
    }

    @Test
    public void useStepVerifier() {
        StepVerifier.create(jokeService.getJokeAsync("Craig", "Walls"))
                .assertNext(joke -> {
                    log.info("Joke:: {}", joke);
                    Assertions.assertThat(joke).isNotEmpty();
                    Assertions.assertThat(joke).contains("Craig");
                })
                .verifyComplete();
    }

}
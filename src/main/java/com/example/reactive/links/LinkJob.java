package com.example.reactive.links;

import com.example.general.common.ShortLink;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
@EnableScheduling
public class LinkJob {

    private final LinksService linksService;
    private final Queue<String> linksQueue;

    public LinkJob(LinksService linksService, Queue<String> linksQueue) {
        this.linksService = linksService;
        this.linksQueue = linksQueue;
    }


    @Scheduled(fixedRate = 1000L)
    public void freeLink(){
        ShortLink shortLink = linksService.randomPull();
        if(shortLink != null){
            linksService.remove(shortLink.getCode());
            linksQueue.add(shortLink.getCode());
        }
    }

    @Scheduled(fixedRate = 500L)
    public void bookLink(){
        String link = linksQueue.poll();
        if(link != null) {
            linksService.save(new ShortLink(link, "google.com"));
        }
    }

    @Scheduled(fixedRate = 500L)
    public void useLink(){
        ShortLink shortLink = linksService.randomPull();
        System.out.println(shortLink);
    }
}

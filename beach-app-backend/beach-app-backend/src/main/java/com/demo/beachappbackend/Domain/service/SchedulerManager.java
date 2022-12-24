package com.example.demo.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Service
public class SchedulerManager {

    @PostConstruct
    private void execute(){
        messageParser.parseData();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(surfCheckerService,1,10,TimeUnit.SECONDS);
    }


@Autowired
SurfCheckerService surfCheckerService;

@Autowired
MessageParser messageParser;
}


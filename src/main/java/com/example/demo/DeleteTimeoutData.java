package com.example.demo;

import com.example.demo.persistence.RecordNotFoundException;
import com.example.demo.persistence.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DeleteTimeoutData {

    @Autowired
    URLService service;

    @Scheduled(fixedRate = 18000000)
    public void cronJobSch() {
        try {
            Date timeWindow = new Date();
            timeWindow.setTime(new Date().getTime() - 18000000);
            service.selectTimeoutCreated(timeWindow);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }
    }
}
package com.example.demo;

import com.example.demo.persistence.RecordNotFoundException;
import com.example.demo.persistence.URLEntity;
import com.example.demo.persistence.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class URLController {

    @Autowired
    URLService service;
    private final Map<String, String> URLHashMap = new HashMap<String, String>();

    @GetMapping("/short")
    public Long shortURL(@RequestParam(name = "url", required = false) String url) {

        Long md5Hash = Long.valueOf(url.hashCode());

        URLEntity urlEntity = new URLEntity();

        urlEntity.setId(md5Hash);
        urlEntity.setUrl(url);

        try {
            URLEntity updated = service.createOrUpdateURL(urlEntity);
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        }

        return md5Hash;
    }

    @GetMapping("/long")
    public String longURL(@RequestParam(name = "tiny", required = false) Long tiny) {

        String result = "";
        URLEntity entity = null;
        try {
            entity = service.getURLById(tiny);
            result = entity.getUrl();
        } catch (RecordNotFoundException e) {
            result = "URL not found in DB";
        }

        return result;
    }
}

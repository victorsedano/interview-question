package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GreetingController {

    private Map<String, String> URLHashMap = new HashMap<String, String>();

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
        return "greeting " + name;
    }

    @GetMapping("/short")
    public String shortURL(@RequestParam(name = "url", required = false) String url) {

        String md5Hash = getMD5Hash(url);
        URLHashMap.put(md5Hash, url);
        return md5Hash;
    }

    @GetMapping("/long")
    public String longURL(@RequestParam(name = "tiny", required = false) String tiny) {

        String result = URLHashMap.get(tiny);
        return result;
    }

    // Java method to create MD5 checksum
    private static String getMD5Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return DatatypeConverter.printHexBinary(hash).toLowerCase();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}

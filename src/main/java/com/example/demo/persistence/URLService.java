package com.example.demo.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class URLService {

    @Autowired
    URLRepository repository;

    public URLEntity getURLById(Long id) throws RecordNotFoundException {
        Optional<URLEntity> url = repository.findById(id);

        if (url.isPresent()) {
            return url.get();
        } else {
            throw new RecordNotFoundException("No URL record exist for given id");
        }
    }

    public void selectTimeoutCreated(Date timeWindow) throws RecordNotFoundException {
        repository.deleteTimeout(timeWindow);
    }

    public URLEntity createOrUpdateURL(URLEntity entity) throws RecordNotFoundException {
        Optional<URLEntity> url = repository.findById(entity.getId());

        if (url.isPresent()) {
            URLEntity newEntity = url.get();
            newEntity.setId(entity.getId());
            newEntity = repository.save(newEntity);
            return newEntity;
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }
}

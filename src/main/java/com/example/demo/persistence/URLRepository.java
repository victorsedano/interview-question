package com.example.demo.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;


@Repository
public interface URLRepository
        extends CrudRepository<URLEntity, Long> {

    @Transactional
    @Modifying
    @Query("delete from URLEntity ue where ue.created<:timeWindow ")
    void deleteTimeout(@Param("timeWindow") Date timeWindow);
}

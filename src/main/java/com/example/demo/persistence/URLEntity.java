package com.example.demo.persistence;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TBL_URL")
public class URLEntity {

    @Id
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    public Date created = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

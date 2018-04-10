package com.guru.nowplaying.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Guru on 06-04-2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Videos {



    private String site;

    private String id;

    private String name;

    private String type;

    private String key;

    public String getSite() {
        return site;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

}

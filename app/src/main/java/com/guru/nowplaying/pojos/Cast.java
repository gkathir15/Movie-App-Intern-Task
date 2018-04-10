package com.guru.nowplaying.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Guru on 06-04-2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cast {


    private String id;

    private String name;

    private String gender;

    @JsonProperty("cast_id")
    private String castId;

    @JsonProperty("profile_Path")
    private String profilePath;

    private String character;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCastId() {
        return castId;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public String getCharacter() {
        return character;
    }
}


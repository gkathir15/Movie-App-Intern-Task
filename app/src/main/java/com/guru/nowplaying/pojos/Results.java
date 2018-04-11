package com.guru.nowplaying.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Guru on 11-04-2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

    @JsonProperty("page")
    String mPage;

    @JsonProperty("total_pages")
    String mTotalPages;
}

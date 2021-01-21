package com.vg.hm.samples.usermanagementsvc.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@Builder
@ToString
public class MakesJson {
    @SerializedName("Count")
    @Expose
    private Integer count;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("SearchCriteria")
    @Expose
    private Object searchCriteria;
    @SerializedName("Results")
    @Expose
    private List<Make> results = null;
}

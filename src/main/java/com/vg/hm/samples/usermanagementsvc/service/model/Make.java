package com.vg.hm.samples.usermanagementsvc.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Make{
    @SerializedName("Make_ID")
    @Expose
    private Integer makeID;
    @SerializedName("Make_Name")
    @Expose
    private String makeName;
}

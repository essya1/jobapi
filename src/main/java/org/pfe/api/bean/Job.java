package org.pfe.api.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @JsonIgnore
    private int id;
    private String title;
    private String company;
    private String location;
    private String salary;
    private String type;
    private String remote;
    private String description;
}

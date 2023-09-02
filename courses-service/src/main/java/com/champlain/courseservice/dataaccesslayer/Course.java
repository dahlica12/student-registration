package com.champlain.courseservice.dataaccesslayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {


    private String id;
    private String courseId;
    private String courseNumber;
    private String courseName;
    private Integer numHours;
    private Double numCredits;
    private String department;


}

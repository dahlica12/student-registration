package com.champlain.sectionsservice.businesslayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {

    private String studentId;
    private String firstName;
    private String lastName;
    private String program;

    
}

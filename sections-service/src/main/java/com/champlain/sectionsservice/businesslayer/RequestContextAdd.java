package com.champlain.sectionsservice.businesslayer;


import com.champlain.sectionsservice.dataaccesslayer.Enrollment;
import com.champlain.sectionsservice.presentationlayer.EnrollmentRequestDTO;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestContextAdd {
    private EnrollmentRequestDTO enrollmentRequestDTO;
    private Enrollment enrollment;
    private StudentResponseDTO studentResponseDTO; //get() of this is null
    private CourseResponseDTO courseResponseDTO;



    public RequestContextAdd(EnrollmentRequestDTO enrollmentRequestDTO) {
        this.enrollmentRequestDTO = enrollmentRequestDTO;
    }



}

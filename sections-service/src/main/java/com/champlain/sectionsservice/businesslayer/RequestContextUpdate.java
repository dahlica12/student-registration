package com.champlain.sectionsservice.businesslayer;

import com.champlain.sectionsservice.dataaccesslayer.Enrollment;
import com.champlain.sectionsservice.presentationlayer.EnrollmentRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestContextUpdate {
    private EnrollmentRequestDTO enrollmentRequestDTO;
    private Enrollment enrollment;
    private  StudentResponseDTO studentResponseDTO;
    private CourseResponseDTO courseResponseDTO;

    public RequestContextUpdate(EnrollmentRequestDTO enrollmentRequestDTO) {
        this.enrollmentRequestDTO = enrollmentRequestDTO;
    }
}

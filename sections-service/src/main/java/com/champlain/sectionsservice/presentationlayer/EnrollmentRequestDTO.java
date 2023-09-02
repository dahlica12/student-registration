package com.champlain.sectionsservice.presentationlayer;

import com.champlain.sectionsservice.dataaccesslayer.Semester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequestDTO {

    private Integer enrollmentYear;
    private Semester semester;
    private String studentId;
    private String courseId;

}

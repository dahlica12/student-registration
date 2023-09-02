package com.champlain.sectionsservice.businesslayer;

import com.champlain.sectionsservice.presentationlayer.EnrollmentRequestDTO;
import com.champlain.sectionsservice.presentationlayer.EnrollmentResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EnrollmentService {

    Flux<EnrollmentResponseDTO> getAllEnrollments();
    Mono<EnrollmentResponseDTO> getEnrollmentByEnrollmentId(String enrollmentId);
    Flux<EnrollmentResponseDTO> getEnrollmentsByStudentId(String studentId);
    Flux<EnrollmentResponseDTO> getEnrollmentsByCourseId(String courseId);
    Flux<EnrollmentResponseDTO> getEnrollmentsByYear(Integer enrollmentYear);
    Mono<EnrollmentResponseDTO> addEnrollment(Mono<EnrollmentRequestDTO> enrollmentRequestDTO);
    Mono<EnrollmentResponseDTO> updateEnrollmentByEnrollmentId(Mono<EnrollmentRequestDTO> enrollmentRequestDTO, String enrollmentId);
    Mono<Void> deleteEnrollment(String enrollmentId);

}

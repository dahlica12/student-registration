package com.champlain.sectionsservice.dataaccesslayer;

import com.champlain.sectionsservice.businesslayer.StudentResponseDTO;
import com.champlain.sectionsservice.presentationlayer.EnrollmentResponseDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface EnrollmentRepository extends ReactiveCrudRepository<Enrollment, String> {

    public Mono<Enrollment> findEnrollmentByEnrollmentId(String enrollmentId);
    public Flux<Enrollment> findEnrollmentByStudentId(String studentId);
    public Flux<Enrollment> findEnrollmentByCourseId(String courseId);
    public Flux<Enrollment> findEnrollmentByEnrollmentYear(Integer enrollmentYear);

}

package com.champlain.sectionsservice.businesslayer;

import com.champlain.sectionsservice.dataaccesslayer.EnrollmentRepository;
import com.champlain.sectionsservice.domainclientlayer.CourseClient;
import com.champlain.sectionsservice.domainclientlayer.StudentClient;
import com.champlain.sectionsservice.presentationlayer.EnrollmentRequestDTO;
import com.champlain.sectionsservice.presentationlayer.EnrollmentResponseDTO;
import com.champlain.sectionsservice.utils.EntityDTOUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService{


    private final EnrollmentRepository enrollmentRepository;
    private final StudentClient studentClient;
    private final CourseClient courseClient;


    @Override
    public Flux<EnrollmentResponseDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .map(EntityDTOUtil::toEnrollmentResponseDTO);
    }

    @Override
    public Mono<EnrollmentResponseDTO> getEnrollmentByEnrollmentId(String enrollmentId) {
        return enrollmentRepository.findEnrollmentByEnrollmentId(enrollmentId)
                .map(EntityDTOUtil::toEnrollmentResponseDTO);
    }

    @Override
    public Flux<EnrollmentResponseDTO> getEnrollmentsByStudentId(String studentId) {
        return enrollmentRepository.findEnrollmentByStudentId(studentId)
                .map(EntityDTOUtil::toEnrollmentResponseDTO);

    }

    @Override
    public Flux<EnrollmentResponseDTO> getEnrollmentsByCourseId(String courseId) {
        return enrollmentRepository.findEnrollmentByCourseId(courseId)
                .map(EntityDTOUtil::toEnrollmentResponseDTO);
    }

    @Override
    public Flux<EnrollmentResponseDTO> getEnrollmentsByYear(Integer enrollmentYear) {
        return enrollmentRepository.findEnrollmentByEnrollmentYear(enrollmentYear)
                .map(EntityDTOUtil::toEnrollmentResponseDTO);
    }

    @Override
    public Mono<EnrollmentResponseDTO> addEnrollment(Mono<EnrollmentRequestDTO> enrollmentRequestDTO) {
        return enrollmentRequestDTO


                .map(RequestContextAdd::new)
                .flatMap(this::studentRequestResponse)
                .flatMap(this::courseRequestResponse)
                .map(EntityDTOUtil::toEnrollmentEntity)
                .map(enrollmentRepository::save)
                .flatMap(entity -> entity)
                .doOnNext(i -> System.out.println("The entity student " + i.getStudentId()))
                .map(EntityDTOUtil::toEnrollmentResponseDTO);


    }

    @Override
    public Mono<EnrollmentResponseDTO> updateEnrollmentByEnrollmentId(Mono<EnrollmentRequestDTO> enrollmentRequestDTO, String enrollmentId) {
        return null;
//                enrollmentRepository.findEnrollmentByEnrollmentId(enrollmentId)
//                .flatMap(r -> enrollmentRequestDTO
//                        .map(EntityDTOUtil::toEnrollmentEntity)
//                        .doOnNext(e -> e.))
    }

    @Override
    public Mono<Void> deleteEnrollment(String enrollmentId) {
        return enrollmentRepository.findEnrollmentByEnrollmentId(enrollmentId)
                .flatMap(enrollmentRepository::delete);
    }

    private Mono<RequestContextAdd> studentRequestResponse(RequestContextAdd rc) {
        return this.studentClient.getStudentByStudentId(rc.getEnrollmentRequestDTO().getStudentId())
                .doOnNext(rc::setStudentResponseDTO)
                .thenReturn(rc);
    }

    private Mono<RequestContextAdd> courseRequestResponse(RequestContextAdd rc){
        return this.courseClient.getCourseByCourseId(rc.getEnrollmentRequestDTO().getCourseId())
                .doOnNext(rc::setCourseResponseDTO)
                .thenReturn(rc);


    }
}

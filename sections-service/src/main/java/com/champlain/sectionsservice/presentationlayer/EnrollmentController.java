package com.champlain.sectionsservice.presentationlayer;

import com.champlain.sectionsservice.businesslayer.EnrollmentService;
import com.champlain.sectionsservice.utils.EntityDTOUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("enrollments")
@Slf4j
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping()
    public Flux<EnrollmentResponseDTO> getAllEnrollments(){
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping(value = "{enrollmentId}")
    public Mono<ResponseEntity<EnrollmentResponseDTO>> getEnrollmentByEnrollmentId(@PathVariable String enrollmentId){
        return enrollmentService.getEnrollmentByEnrollmentId(enrollmentId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "students/{studentId}")
    public Flux<EnrollmentResponseDTO> getEnrollmentByStudentId(@PathVariable String studentId){
        return enrollmentService.getEnrollmentsByStudentId(studentId);
    }

    @GetMapping(value = "courses/{courseId}")
    public Flux<EnrollmentResponseDTO> getEnrollmentByCourseId(@PathVariable String courseId){
        return enrollmentService.getEnrollmentsByCourseId(courseId);
    }

    @GetMapping(value = "year/{enrollmentYear}")
    public Flux<EnrollmentResponseDTO> getEnrollmentByYear(@PathVariable Integer enrollmentYear){
        return enrollmentService.getEnrollmentsByYear(enrollmentYear);
    }

    @PostMapping()
    public Mono<ResponseEntity<EnrollmentResponseDTO>> addEnrollment(@RequestBody Mono<EnrollmentRequestDTO> enrollmentRequestDTO) {

        return enrollmentService.addEnrollment(enrollmentRequestDTO)
                .doOnNext(i -> System.out.println("I'm in addEnrollment in Service - before map"))
                .map(e -> ResponseEntity.status(HttpStatus.CREATED).body(e))
                .doOnNext(i -> System.out.println("I'm in addEnrollment in Controller" + i.toString()))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }


    @PutMapping(value = "{enrollmentId}")
    public Mono<ResponseEntity<EnrollmentResponseDTO>> updateEnrollment(@RequestBody Mono<EnrollmentRequestDTO> enrollmentRequestDTO, @PathVariable String enrollmentId){
        return enrollmentService.updateEnrollmentByEnrollmentId(enrollmentRequestDTO, enrollmentId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "{enrollmentId}")
    public Mono<ResponseEntity<Void>> deleteEnrollment(@PathVariable String enrollmentId){
        return enrollmentService.deleteEnrollment(enrollmentId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

}

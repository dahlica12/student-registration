package com.champlain.courseservice.presentationlayer;

import com.champlain.courseservice.businesslayer.CourseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CourseResponseDTO> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping(value = "{courseId}")
    public Mono<ResponseEntity<CourseResponseDTO>> getCourseByCourseId(@PathVariable String courseId){
        return courseService.getCourseByCourseId(courseId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Mono<ResponseEntity<CourseResponseDTO>> addCourse(@RequestBody Mono<CourseRequestDTO> courseRequestDTO){
        return courseService.addCourse(courseRequestDTO)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping(value = "{courseId}")
    public Mono<ResponseEntity<CourseResponseDTO>> updateCourse(@RequestBody Mono<CourseRequestDTO> courseRequestDTO, @PathVariable String courseId){
        return courseService.updateCourseByCourseId(courseRequestDTO, courseId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{courseId}")
    public Mono<ResponseEntity<Void>> deleteCourse(@PathVariable String courseId){
        return courseService.deleteCourse(courseId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}

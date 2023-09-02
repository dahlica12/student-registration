package com.champlain.courseservice.businesslayer;

import com.champlain.courseservice.dataaccesslayer.Course;
import com.champlain.courseservice.presentationlayer.CourseRequestDTO;
import com.champlain.courseservice.presentationlayer.CourseResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {

    Flux<CourseResponseDTO> getAllCourses();
    Mono<CourseResponseDTO> getCourseByCourseId(String courseId);
    Mono<CourseResponseDTO> addCourse(Mono<CourseRequestDTO> courseRequestDTO);
    Mono<CourseResponseDTO> updateCourseByCourseId(Mono<CourseRequestDTO> courseRequestDTO, String courseId);
    Mono<Void> deleteCourse(String courseId);

}

package com.champlain.courseservice.dataaccesslayer;

import com.champlain.courseservice.presentationlayer.CourseResponseDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CourseRepository extends ReactiveMongoRepository<Course, String> {

    public Mono<Course> findCourseByCourseId(String courseId);
}

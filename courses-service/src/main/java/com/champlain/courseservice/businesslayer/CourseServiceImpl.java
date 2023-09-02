package com.champlain.courseservice.businesslayer;

import com.champlain.courseservice.dataaccesslayer.CourseRepository;
import com.champlain.courseservice.presentationlayer.CourseRequestDTO;
import com.champlain.courseservice.presentationlayer.CourseResponseDTO;
import com.champlain.courseservice.utils.EntityDTOUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;


    @Override
    public Flux<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll()
                .map(EntityDTOUtil::toCourseResponseDTO);
    }

    @Override
    public Mono<CourseResponseDTO> getCourseByCourseId(String courseId) {
        return courseRepository
                .findCourseByCourseId(courseId)
                .map(EntityDTOUtil::toCourseResponseDTO);
    }

    @Override
    public Mono<CourseResponseDTO> addCourse(Mono<CourseRequestDTO> courseRequestDTO) {
        return courseRequestDTO
                .map(EntityDTOUtil::toCourseEntity)
                .doOnNext(e -> e.setCourseId(EntityDTOUtil.generateUUIDString()))
                .flatMap(courseRepository::insert)
                .map(EntityDTOUtil::toCourseResponseDTO);
    }

    @Override
    public Mono<CourseResponseDTO> updateCourseByCourseId(Mono<CourseRequestDTO> courseRequestDTO, String courseId) {
        return courseRepository.findCourseByCourseId(courseId)
                .flatMap(c -> courseRequestDTO
                        .map(EntityDTOUtil::toCourseEntity)
                        .doOnNext(e -> e.setId(e.getId()))
                        .doOnNext(e -> e.setCourseId(e.getCourseId()))
                        .doOnNext(e -> e.setCourseName(e.getCourseName()))
                        .doOnNext(e -> e.setCourseNumber(e.getCourseNumber()))
                        .doOnNext(e -> e.setNumHours(e.getNumHours()))
                        .doOnNext(e -> e.setNumCredits(e.getNumCredits()))
                        .doOnNext(e -> e.setDepartment(e.getDepartment())))
                .flatMap(courseRepository::save)

                .map(EntityDTOUtil::toCourseResponseDTO);
    }

    @Override
    public Mono<Void> deleteCourse(String courseId) {
        return courseRepository.findCourseByCourseId(courseId)
                .flatMap(courseRepository::delete);
    }


}

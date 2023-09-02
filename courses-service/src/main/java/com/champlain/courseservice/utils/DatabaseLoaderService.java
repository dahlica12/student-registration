package com.champlain.courseservice.utils;

import com.champlain.courseservice.businesslayer.CourseService;
import com.champlain.courseservice.dataaccesslayer.Course;
import com.champlain.courseservice.dataaccesslayer.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DatabaseLoaderService  implements CommandLineRunner {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {

        Course course1 = Course.builder()
                .courseNumber("420B02")
                .courseName("Mobile Development with IOS")
                .numHours(4)
                .numCredits(12.3)
                .department("Computer Science")
                .courseId(UUID.randomUUID().toString())
                .build();

        Course course2 = Course.builder()
                .courseNumber("420A01")
                .courseName("Java 1")
                .numHours(6)
                .numCredits(10.0)
                .department("Computer Science")
                .courseId(UUID.randomUUID().toString())
                .build();

        Course course3 = Course.builder()
                .courseNumber("502C02")
                .courseName("Intro to Psychology")
                .numHours(3)
                .numCredits(5.0)
                .department("Psychology")
                .courseId(UUID.randomUUID().toString())
                .build();

        Course course4 = Course.builder()
                .courseNumber("602C02")
                .courseName("Biology")
                .numHours(3)
                .numCredits(7.3)
                .department("Health Science")
                .courseId(UUID.randomUUID().toString())
                .build();

        Course course5 = Course.builder()
                .courseNumber("202D01")
                .courseName("Photoshop")
                .numHours(3)
                .numCredits(7.3)
                .department("Film and Media")
                .courseId(UUID.randomUUID().toString())
                .build();


        Flux.just(course1, course2, course3, course4, course5)
                .flatMap(c -> courseRepository.insert(Mono.just(c))
                .log(c.toString()))
                .subscribe();

    }

}

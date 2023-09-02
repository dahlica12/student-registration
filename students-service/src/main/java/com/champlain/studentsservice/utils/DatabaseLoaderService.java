package com.champlain.studentsservice.utils;

import com.champlain.studentsservice.dataaccesslayer.Student;
import com.champlain.studentsservice.dataaccesslayer.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DatabaseLoaderService implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        Student student1 = Student.builder()
                .firstName("Sam")
                .lastName("Jones")
                .studentId(UUID.randomUUID().toString())
                .program("Comp Sci")
                .build();


        Student student2 = Student.builder()
                .firstName("Petra")
                .lastName("Bueller")
                .studentId(UUID.randomUUID().toString())
                .program("Pure and Applied Science")
                .build();


        Student student3 = Student.builder()
                .firstName("Beth")
                .lastName("Kong")
                .studentId(UUID.randomUUID().toString())
                .program("Math and Comp Sci")
                .build();


        Student student4 = Student.builder()
                .firstName("Jean")
                .lastName("Tremblay")
                .studentId(UUID.randomUUID().toString())
                .program("Graphics and Media")
                .build();


        Flux.just(student1, student2, student3, student4)
                .flatMap(s -> studentRepository.insert(Mono.just(s))
                .log(s.toString()))
                .subscribe(); //always subscribe!

    }
}



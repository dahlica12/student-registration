package com.champlain.studentsservice.businesslayer;

import com.champlain.studentsservice.presentationlayer.StudentRequestDTO;
import com.champlain.studentsservice.presentationlayer.StudentResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

    Flux<StudentResponseDTO> getAllStudents();
    Mono<StudentResponseDTO> addStudent(Mono<StudentRequestDTO> studentRequestDTO);
    Mono<StudentResponseDTO> getStudentByStudentId(String studentId);
    Mono<StudentResponseDTO> updateStudentByStudentId(Mono<StudentRequestDTO> studentRequestDTO, String studentId);
    Mono<Void> deleteStudent(String studentId);
}

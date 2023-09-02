package com.champlain.studentsservice.businesslayer;

import com.champlain.studentsservice.dataaccesslayer.Student;
import com.champlain.studentsservice.dataaccesslayer.StudentRepository;
import com.champlain.studentsservice.presentationlayer.StudentRequestDTO;
import com.champlain.studentsservice.presentationlayer.StudentResponseDTO;
import com.champlain.studentsservice.utils.EntityDTOUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;


    @Override
    public Flux<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .map(EntityDTOUtil::toStudentResponseDTO);
    }

    @Override
    public Mono<StudentResponseDTO> addStudent(Mono<StudentRequestDTO> studentRequestDTO){
        return studentRequestDTO
                .map(EntityDTOUtil::toStudentEntity)
                .doOnNext(e -> e.setStudentId(EntityDTOUtil.generateUUIDString()))
                .flatMap(studentRepository::insert)
                .map(EntityDTOUtil::toStudentResponseDTO);
    }

    @Override
    public Mono<StudentResponseDTO> getStudentByStudentId(String studentId) {
        return studentRepository
                .findStudentByStudentId(studentId)
                .map(EntityDTOUtil::toStudentResponseDTO);
    }

    @Override
    public Mono<StudentResponseDTO> updateStudentByStudentId(Mono<StudentRequestDTO> studentRequestDTO, String studentId) {
        return studentRepository.findStudentByStudentId(studentId)
                .flatMap(s -> studentRequestDTO
                .map(EntityDTOUtil::toStudentEntity)
                .doOnNext(e -> e.setStudentId(e.getStudentId()))
                                .doOnNext(e -> e.setFirstName(e.getFirstName()))
                                .doOnNext(e -> e.setLastName(e.getLastName()))
                                .doOnNext(e -> e.setProgram(e.getProgram()))
                .doOnNext(e -> e.setId(e.getId()))
                )
                .flatMap(studentRepository::save)

                .map(EntityDTOUtil::toStudentResponseDTO);
    }

    @Override
    public Mono<Void> deleteStudent(String studentId) {
       return studentRepository.findStudentByStudentId(studentId)
               .flatMap(studentRepository::delete);
               //.then();

    }

}

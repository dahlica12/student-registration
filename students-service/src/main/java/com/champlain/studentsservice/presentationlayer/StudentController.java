package com.champlain.studentsservice.presentationlayer;

import com.champlain.studentsservice.businesslayer.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

@RestController
@RequestMapping("students")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StudentResponseDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("{studentId}")
    public Mono<ResponseEntity<StudentResponseDTO>> getStudentById(@PathVariable String studentId){
        return studentService.getStudentByStudentId(studentId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public Mono<ResponseEntity<StudentResponseDTO>> addStudent(@RequestBody Mono<StudentRequestDTO> studentRequestDTO) throws URISyntaxException {
        return studentService.addStudent(studentRequestDTO)
                .map(s -> ResponseEntity.status(HttpStatus.CREATED).body(s))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("{studentId}")
    public Mono<ResponseEntity<StudentResponseDTO>> updateStudent(@PathVariable String studentId, @RequestBody Mono<StudentRequestDTO> studentRequestDTO){
        return studentService.updateStudentByStudentId(studentRequestDTO, studentId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping(value = "{studentId}")
    public Mono<ResponseEntity<Void>> deleteStudent(@PathVariable String studentId){
        return studentService.deleteStudent(studentId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }


}

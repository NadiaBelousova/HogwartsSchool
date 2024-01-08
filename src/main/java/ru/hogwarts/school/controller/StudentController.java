package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent (Student student){
       return studentService.createStudent(student);
    }

    @GetMapping ("{id}")
    public ResponseEntity <Student> findStudent(@PathVariable long id) {
        Student studentFound= studentService.findStudent(id);
        if (studentFound==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentFound);
    }

    @PutMapping
    public ResponseEntity <Student> editStudent(Student student) {
        Student studentToChange= studentService.editStudent(student);
        if (studentToChange == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentToChange);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity <Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();

    }
    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }


}

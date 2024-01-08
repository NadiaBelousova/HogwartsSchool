package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

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
    public Student findStudent(@PathVariable long id) {
        return studentService.findStudent(id);
    }

    @PutMapping
    public Student editStudent(Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping ("{id}")
    public Student deleteStudent(@PathVariable long id) {
        return studentService.deleteStudent(id);

    }

    @GetMapping("{age}")
    public Collection<Student> sortStudentsByAge(@PathVariable int age) {
        return studentService.sortStudents(age);
    }
}

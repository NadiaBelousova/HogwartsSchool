package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/createStudent")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/findStudentById/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable long id) {
        Student studentFound = studentService.findStudent(id);
        if (studentFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentFound);
    }

    @PutMapping("/editStudent")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student studentToChange = studentService.editStudent(student);
        if (studentToChange == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentToChange);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/findStudentByAge/{age}")
    public ResponseEntity<Collection<Student>> findStudents(@PathVariable int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/findStudentByAgeBetween")
    public ResponseEntity<Collection<Student>> findByAgeBetween(@RequestParam(required = true) int max, @RequestParam(required = true) int min) {
        List<Student> studentToFind = studentService.findByAgeBetween(min, max);
        if (studentToFind == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentToFind);
    }

    @GetMapping("/facultyOfStudent")
    public ResponseEntity<Faculty> findFacultyOfStudent(@RequestParam long id) {
        Faculty facultyToFind = studentService.findFacultyOfStudent(id);
        if (facultyToFind == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyToFind);
    }

}

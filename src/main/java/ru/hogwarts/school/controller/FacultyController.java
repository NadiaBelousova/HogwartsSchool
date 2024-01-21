package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(Faculty faculty) {
        Faculty faculty1 = facultyService.editFaculty(faculty);
        if (faculty1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty1);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/findFacultyByColorOrName")
    public ResponseEntity<Collection<Faculty>> findByNameOrColor(@RequestParam (required = false) String color, @RequestParam (required = false) String name) {
        return ResponseEntity.ok(facultyService.findByNameOrColor(color, name));

    }


}

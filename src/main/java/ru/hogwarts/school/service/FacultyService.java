package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty createFaculty(Faculty faculty) {
        logger.info("был вызван метод, чтобы создать факультет");

        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("был вызван метод, чтобы найти факультет по идентификатору");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.warn("был вызван метод, чтобы редактировать факультет");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.warn("был вызван метод, чтобы удалить факультет");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByNameOrColor(String color, String name) {
        logger.info("был вызван метод, чтобы найти факультет по имени или цвету");
        return facultyRepository.getByNameIgnoreCaseOrColorIgnoreCase(color, name);
    }

    public Collection<Student> getAllStudentOfFaculty(long id) {
        logger.info("был вызван метод, чтобы получить всех студентов факультета по его идентификатору");
        return facultyRepository.getReferenceById(id).getStudents();
    }

}



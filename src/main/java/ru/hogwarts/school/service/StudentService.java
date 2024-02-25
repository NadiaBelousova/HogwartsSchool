package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    Logger logger = LoggerFactory.getLogger(StudentService.class);


    public Student createStudent(Student student) {
        logger.info("был вызван метод, чтобы создать студента");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {

        logger.info("был вызван метод, чтобы найти студента по идентификатору");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.warn("был вызван метод, чтобы редактировать студента");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {

        logger.warn("был вызван метод, чтобы удалить студента");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("был вызван метод, чтобы найти студента по возрасту");
        return studentRepository.findByAge(age);
    }

    public List<Student> findByAgeBetween(int max, int min) {
        logger.info("был вызван метод, чтобы найти студентов в диапозоне разных возрастов");
        return studentRepository.findByAgeBetween(max, min);
    }

    public Faculty findFacultyOfStudent(long id) {
        logger.info("был вызван метод, чтобы найти факультет студента");
        Optional <Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get().getFaculty();
        } else {
            return null;
        }
    }

    public List<Student> getAllStudents() {
        logger.info("был вызван метод, чтобы найти всех студентов");
       return studentRepository.findAll();
    }
    public int getAmountOfStudents () {
        logger.info("был вызван метод, чтобы получить количество всех студентов");
       return studentRepository.amountOfStudents();
    }
    public int getAverageAge () {
        logger.info("был вызван метод, чтобы получить средний возраст студентов");
        return studentRepository.averageAge();
    }
    public List<Student> getLastStudents() {
        logger.info("был вызван метод, чтобы получить последних добавленных студентов");
       return studentRepository.getLastStudents();
    }
}

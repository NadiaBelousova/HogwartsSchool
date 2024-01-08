package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {
    HashMap<Long, Student> studentHashMap = new HashMap<>();
    private long lastId=0;

    public Student createStudent(@RequestBody Student student) {
        student.setId (++lastId);
        studentHashMap.put(student.getId(), student);
        return student;
    }

    public Student findStudent(long id) {
      return studentHashMap.get(id);
    }

    public Student editStudent(Student student) {
        if (!studentHashMap.containsKey(student.getId())) {
            return null;
        }
        studentHashMap.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id) {
        return studentHashMap.remove(id);
    }
    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : studentHashMap.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }


}

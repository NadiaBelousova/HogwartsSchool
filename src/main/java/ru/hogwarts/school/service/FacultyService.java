package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;


@Service
public class FacultyService {
    HashMap <Long, Faculty> facultyHashMap = new HashMap<>();
    private long lastId=0;
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        faculty.setId (++lastId);
        facultyHashMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultyHashMap.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (!facultyHashMap.containsKey(faculty.getId())) {
            return null;
        }
        facultyHashMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return facultyHashMap.remove(id);
    }
    public Collection <Faculty> findByColor(String color) {
        ArrayList<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultyHashMap.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }



}

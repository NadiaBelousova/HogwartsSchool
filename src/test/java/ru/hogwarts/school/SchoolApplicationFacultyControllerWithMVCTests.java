//package ru.hogwarts.school;
//
//import net.minidev.json.JSONObject;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import ru.hogwarts.school.controller.AvatarController;
//import ru.hogwarts.school.controller.FacultyController;
//import ru.hogwarts.school.model.Faculty;
//import ru.hogwarts.school.model.Student;
//import ru.hogwarts.school.repository.FacultyRepository;
//import ru.hogwarts.school.service.AvatarService;
//import ru.hogwarts.school.service.FacultyService;
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class SchoolApplicationFacultyControllerWithMVCTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private FacultyRepository facultyRepository;
//
//    @SpyBean
//    private FacultyService facultyService;
//
//    @SpyBean
//    private AvatarService avatarService;
//
//    @InjectMocks
//    private FacultyController facultyController;
//
//    @InjectMocks
//    private AvatarController avatarController;
//
//    @Test
//    public void testAddFaculty() throws Exception {
//        JSONObject userObject = new JSONObject();
//        Faculty facultyTest=new Faculty();
//        facultyTest.setId(1L);
//        facultyTest.setName("test");
//        facultyTest.setColor("test");
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(facultyTest);
//
//        mockMvc.perform(post("/faculty")
//                        .content(userObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(facultyTest.getId()))
//                .andExpect(jsonPath("$.name").value(facultyTest.getName()))
//                .andExpect(jsonPath("$.color").value(facultyTest.getColor()))
//                .andExpect(jsonPath("$.students").value(facultyTest.getStudents()));
//    }
//
//    @Test
//    public void testGetFacultyById() throws Exception {
//        Faculty facultyTest=new Faculty();
//        facultyTest.setId(1L);
//        facultyTest.setName("test");
//        facultyTest.setColor("test");
//        when(facultyRepository.findById(1L)).thenReturn(Optional.ofNullable(facultyTest));
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/faculty/" + facultyTest.getId())
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(facultyTest.getId()))
//                .andExpect(jsonPath("$.name").value(facultyTest.getName()))
//                .andExpect(jsonPath("$.color").value(facultyTest.getColor()))
//                .andExpect(jsonPath("$.students").value(facultyTest.getStudents()));
//    }
//
//    @Test
//    public void testEditFaculty() throws Exception {
//        Faculty facultyEdit = new Faculty(2L, "NewNameOfFaculty", "NewColorOfFaculty", null);
//        JSONObject userObject = new JSONObject();
//        userObject.put("id", facultyEdit.getId());
//        userObject.put("name", facultyEdit.getName());
//        userObject.put("color", facultyEdit.getColor());
//        userObject.put("students", null);
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(facultyEdit);
//
//        mockMvc.perform(put("/faculty/" + facultyEdit.getId())
//                        .content(userObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(facultyEdit.getId()))
//                .andExpect(jsonPath("$.name").value(facultyEdit.getName()))
//                .andExpect(jsonPath("$.color").value(facultyEdit.getColor()))
//                .andExpect(jsonPath("$.students").value(facultyEdit.getStudents()));
//    }
//
//    @Test
//    public void testDeleteFaculty() throws Exception {
//        Faculty facultyTest=new Faculty();
//        facultyTest.setId(1L);
//        facultyTest.setName("test");
//        facultyTest.setColor("test");
//        JSONObject userObject = new JSONObject();
//        userObject.put("id", facultyTest.getId());
//        userObject.put("name", facultyTest.getName());
//        userObject.put("color", facultyTest.getColor());
//        userObject.put("students", facultyTest.getStudents());
//
//        when(facultyRepository.save(any(Faculty.class))).thenReturn(facultyTest);
//
//        mockMvc.perform(delete("/faculty/" + facultyTest.getId())
//                        .content(userObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void testGetAllStudentsFaculty() throws Exception {
//        Faculty facultyTest=new Faculty();
//        facultyTest.setId(1L);
//        facultyTest.setName("test");
//        facultyTest.setColor("test");
//        Student student=new Student(10L,"test",12,facultyTest);
//        List<Faculty> actualFaculties = new ArrayList<>();
//        actualFaculties.add(facultyTest);
//        List<Faculty> expFaculties = new ArrayList<>();
//       expFaculties.add(facultyTest);
//        when(facultyRepository.findAll()).thenReturn(actualFaculties);
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/faculty")
//                .accept(MediaType.APPLICATION_JSON));
//
//        assertEquals(actualFaculties.size(), expFaculties.size());
//        for (int i = 0; i < actualFaculties.size(); i++) {
//            assertEquals(expFaculties.get(i).getName(), actualFaculties.get(i).getName());
//        }
//    }
//
//    @Test
//    public void testFindByName() throws Exception {
//        Faculty facultyTest1=new Faculty();
//        facultyTest1.setId(1L);
//        facultyTest1.setName("test");
//        facultyTest1.setColor("blue");
//        Faculty facultyTest2=new Faculty();
//        facultyTest1.setId(2L);
//        facultyTest1.setName("test");
//        facultyTest1.setColor("blue");
//
//        when(facultyRepository.getByNameIgnoreCaseOrColorIgnoreCase(facultyTest1.getName(),facultyTest1.getColor())).thenReturn((Collection<Faculty>) facultyTest1);
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/faculty/" + facultyTest1.getName())
//                .accept(MediaType.APPLICATION_JSON));
//            assertEquals(facultyTest1.getName(), facultyTest2.getName());
//        when(facultyRepository.getByNameIgnoreCaseOrColorIgnoreCase(facultyTest1.getColor(),facultyTest1.getColor())).thenReturn((Collection<Faculty>) facultyTest1);
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/faculty/" + facultyTest1.getColor())
//                .accept(MediaType.APPLICATION_JSON));
//        assertEquals(facultyTest1.getColor(), facultyTest2.getColor());
//        }
//
////    @Test
////    public void testFindByColor() throws Exception {
////        List<Faculty> actualFaculties = LIST_FACULTY_1;
////        when(facultyRepository.findByNameContainingIgnoreCase(FACULTY_1.getName())).thenReturn(LIST_FACULTY_1);
////        mockMvc.perform(MockMvcRequestBuilders
////                .get("/faculty/" + FACULTY_1.getColor())
////                .accept(MediaType.APPLICATION_JSON));
////        assertEquals(LIST_FACULTY_1.size(), actualFaculties.size());
////        for (int i = 0; i < LIST_FACULTY_1.size(); i++) {
////            assertEquals(LIST_FACULTY_1.get(i).getColor(), actualFaculties.get(i).getColor());
////        }
////    }
//
////    @Test
////    public void testFindByNameAndColor() throws Exception {
////        List<Faculty> actualFaculties = LIST_FACULTY_3;
////        when(facultyRepository.findByNameContainingIgnoreCase(FACULTY_3.getName())).thenReturn(LIST_FACULTY_3);
////        mockMvc.perform(MockMvcRequestBuilders
////                .get("/faculty/" + FACULTY_3.getName() + FACULTY_3.getColor())
////                .accept(MediaType.APPLICATION_JSON));
////        assertEquals(LIST_FACULTY_3.size(), actualFaculties.size());
////        for (int i = 0; i < LIST_FACULTY_3.size(); i++) {
////            assertEquals(LIST_FACULTY_3.get(i).getName(), actualFaculties.get(i).getName());
////            assertEquals(LIST_FACULTY_3.get(i).getColor(), actualFaculties.get(i).getColor());
////        }
////    }
//
//    @Test
//    public void testGetAllStudentsOfFaculty() throws Exception {
//        Faculty facultyTest=new Faculty();
//        facultyTest.setId(1L);
//        facultyTest.setName("test");
//        facultyTest.setColor("test");
//        Student student=new Student(10L,"test",12,facultyTest);
//        List<Faculty> actualFaculties = new ArrayList<>();
//        actualFaculties.add(facultyTest);
//        List<Faculty> expFaculties = new ArrayList<>();
//        expFaculties.add(facultyTest);
//        List<Student> actualStudents = facultyTest.getStudents();
//        when(facultyRepository.getByNameIgnoreCaseOrColorIgnoreCase(
//                facultyTest.getName(),
//                facultyTest.getColor())).thenReturn((Collection<Faculty>) facultyTest);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/faculty/" +
//                        facultyTest.getName() +
//                        facultyTest.getColor())
//                .accept(MediaType.APPLICATION_JSON));
//        assertEquals(facultyTest.getStudents().size(), actualStudents.size());
//        for (int i = 0; i < facultyTest.getStudents().size(); i++) {
//            assertEquals(facultyTest.getStudents(), actualStudents);
//        }
//    }
//}
//

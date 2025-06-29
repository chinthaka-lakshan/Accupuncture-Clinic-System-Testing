package com.Accupuncture.Accupuncture.controller;

import com.Accupuncture.Accupuncture.entity.Student;
import com.Accupuncture.Accupuncture.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveStudent_ShouldReturnSavedStudent() throws Exception {
        Student student = new Student(1, "Test Student", "555-1234");

        when(studentService.saveDetails(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentID").value(1))
                .andExpect(jsonPath("$.studentName").value("Test Student"));
    }

    @Test
    void getDetails_ShouldReturnAllStudents() throws Exception {
        Student student1 = new Student(1, "Student 1", "555-1111");
        Student student2 = new Student(2, "Student 2", "555-2222");

        when(studentService.getAllDetails()).thenReturn(Arrays.asList(student1, student2));

        mockMvc.perform(get("/getAllStudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentName").value("Student 1"))
                .andExpect(jsonPath("$[1].studentName").value("Student 2"));
    }

    @Test
    void getDetailsByID_ShouldReturnStudent() throws Exception {
        Student student = new Student(1, "Test Student", "555-1234");

        when(studentService.getStudentDetailsByID(1)).thenReturn(student);

        mockMvc.perform(get("/getStudentBYID/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Test Student"));
    }

    @Test
    void getDetailsByID_WithInvalidId_ShouldReturnNotFound() throws Exception {
        when(studentService.getStudentDetailsByID(99)).thenReturn(null);

        mockMvc.perform(get("/getStudentBYID/99"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void updateStudentDetails_ShouldReturnUpdatedStudent() throws Exception {
        Student updatedStudent = new Student(1, "Updated Student", "555-9999");

        when(studentService.updateDetails(any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/updateStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentName").value("Updated Student"));
    }

    @Test
    void deleteStudentDetails_ShouldReturnSuccessMessage() throws Exception {
        when(studentService.deleteStudent(1)).thenReturn("deleted1");

        mockMvc.perform(delete("/deleteStudent/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("deleted1"));
    }
}
package com.Accupuncture.Accupuncture.service;

import com.Accupuncture.Accupuncture.entity.Student;
import com.Accupuncture.Accupuncture.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        student1 = new Student();
        student1.setStudentID(1);
        student1.setStudentName("Alice Johnson");
        student1.setContactNo("555-1111");

        student2 = new Student();
        student2.setStudentID(2);
        student2.setStudentName("Bob Smith");
        student2.setContactNo("555-2222");
    }

    @Test
    void saveDetails_ShouldSaveAndReturnStudent() {
        when(studentRepository.save(student1)).thenReturn(student1);

        Student savedStudent = studentService.saveDetails(student1);

        assertNotNull(savedStudent);
        assertEquals("Alice Johnson", savedStudent.getStudentName());
        verify(studentRepository, times(1)).save(student1);
    }

    @Test
    void getAllDetails_ShouldReturnAllStudents() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<Student> students = studentService.getAllDetails();

        assertEquals(2, students.size());
        assertEquals("Bob Smith", students.get(1).getStudentName());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void getStudentDetailsByID_WithValidId_ShouldReturnStudent() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student1));

        Student foundStudent = studentService.getStudentDetailsByID(1);

        assertNotNull(foundStudent);
        assertEquals(1, foundStudent.getStudentID());
        verify(studentRepository, times(1)).findById(1);
    }

    @Test
    void getStudentDetailsByID_WithInvalidId_ShouldReturnNull() {
        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        Student foundStudent = studentService.getStudentDetailsByID(99);

        assertNull(foundStudent);
        verify(studentRepository, times(1)).findById(99);
    }

    @Test
    void updateDetails_WithExistingStudent_ShouldUpdateAndReturnStudent() {
        Student updatedStudent = new Student();
        updatedStudent.setStudentID(1);
        updatedStudent.setStudentName("Updated Name");
        updatedStudent.setContactNo("555-9999");

        when(studentRepository.findById(1)).thenReturn(Optional.of(student1));
        when(studentRepository.save(student1)).thenReturn(student1);

        Student result = studentService.updateDetails(updatedStudent);

        assertNotNull(result);
        assertEquals("Updated Name", result.getStudentName());
        assertEquals("555-9999", result.getContactNo());
        verify(studentRepository, times(1)).findById(1);
        verify(studentRepository, times(1)).save(student1);
    }

    @Test
    void updateDetails_WithNonExistingStudent_ShouldReturnNull() {
        Student nonExistingStudent = new Student();
        nonExistingStudent.setStudentID(99);

        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        Student result = studentService.updateDetails(nonExistingStudent);

        assertNull(result);
        verify(studentRepository, times(1)).findById(99);
        verify(studentRepository, never()).save(any());
    }

    @Test
    void deleteStudent_WithExistingId_ShouldDeleteAndReturnSuccessMessage() {
        when(studentRepository.existsById(1)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1);

        String result = studentService.deleteStudent(1);

        assertEquals("deleted1", result);
        verify(studentRepository, times(1)).existsById(1);
        verify(studentRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteStudent_WithNonExistingId_ShouldReturnErrorMessage() {
        when(studentRepository.existsById(99)).thenReturn(false);

        String result = studentService.deleteStudent(99);

        assertEquals("Student Id not existed!", result);
        verify(studentRepository, times(1)).existsById(99);
        verify(studentRepository, never()).deleteById(anyInt());
    }
}
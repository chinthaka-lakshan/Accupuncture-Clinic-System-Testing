//---StudentService

package com.Accupuncture.Accupuncture.service;

import com.Accupuncture.Accupuncture.entity.Student;
import com.Accupuncture.Accupuncture.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Service layer for student management operations
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    //Creates a new student record
    public Student saveDetails(Student student){
        return studentRepository.save(student);
    }

    //Retrieves all students
    public List<Student> getAllDetails(){
        return studentRepository.findAll();
    }

    //Retrieves a student by ID
    public Student getStudentDetailsByID(int studentID){
        return studentRepository.findById(studentID).orElse(null);
    }

    //Updates an existing student record
    public Student updateDetails(Student student) {
        Student updateStudent = studentRepository.findById(student.getStudentID()).orElse(null);
        if (updateStudent != null) {
            updateStudent.setStudentName(student.getStudentName());
            updateStudent.setContactNo(student.getContactNo());
            studentRepository.save(updateStudent);
            return updateStudent;
        }
        return null;
    }

    //Deletes a student by ID
    public String deleteStudent(int studentID){
        if(studentRepository.existsById(studentID)){
            studentRepository.deleteById(studentID);
            return "deleted" + studentID;
        }else {
            return "Student Id not existed!";
        }
    }
}

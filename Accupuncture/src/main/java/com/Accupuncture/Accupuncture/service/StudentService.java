//---StudentService

package com.Accupuncture.Accupuncture.service;

import com.Accupuncture.Accupuncture.entity.Student;
import com.Accupuncture.Accupuncture.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student saveDetails(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getAllDetails(){
        return studentRepository.findAll();
    }

    public Student getStudentDetailsByID(int studentID){
        return studentRepository.findById(studentID).orElse(null);
    }

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

    public String deleteStudent(int studentID){
        if(studentRepository.existsById(studentID)){
            studentRepository.deleteById(studentID);
            return "deleted" + studentID;
        }else {
            return "Student Id not existed!";
        }
    }
}

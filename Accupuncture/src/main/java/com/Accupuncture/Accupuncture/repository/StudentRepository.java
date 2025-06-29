//---StudentRepository

package com.Accupuncture.Accupuncture.repository;

import com.Accupuncture.Accupuncture.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}

/**
 * 
 */
package com.springboot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.web.entities.Student;

/**
 * @author smrut
 *
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {

}

package com.demo.arangodbtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.arangodbtest.model.Student;
import com.demo.arangodbtest.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
    private StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }


}

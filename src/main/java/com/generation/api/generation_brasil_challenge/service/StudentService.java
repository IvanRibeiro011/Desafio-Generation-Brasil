package com.generation.api.generation_brasil_challenge.service;

import com.generation.api.generation_brasil_challenge.dtos.StudentDTO;
import com.generation.api.generation_brasil_challenge.exceptions.ResourceNotFoundException;
import com.generation.api.generation_brasil_challenge.model.Student;
import com.generation.api.generation_brasil_challenge.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

        @Transactional(readOnly = true)
        public Page<StudentDTO> findAll(int page,int size) {
            Pageable pageable = Pageable.ofSize(size).withPage(page);
            return repository.findAll(pageable).map(StudentDTO::new);
        }

    private Student findStudentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
    }

    @Transactional(readOnly = true)
    public StudentDTO findById(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found."));
        return new StudentDTO(student);
    }

    @Transactional
    public StudentDTO insert(StudentDTO dto) {
        Student student = new Student();
        copyDtoToEntity(student, dto);
        student = repository.save(student);
        return new StudentDTO(student);
    }

    @Transactional
    public StudentDTO update(Long id, StudentDTO dto) {
        Student student = findStudentById(id);
        copyDtoToEntity(student, dto);
        student = repository.save(student);
        return new StudentDTO(student);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entity not found");
        }
        repository.deleteById(id);
    }

    private void copyDtoToEntity(Student student, StudentDTO dto) {
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setFirstGrade(dto.getFirstGrade());
        student.setSecondGrade(dto.getSecondGrade());
        student.setTeacherName(dto.getTeacherName());
        student.setRoom(dto.getRoom());
    }
}

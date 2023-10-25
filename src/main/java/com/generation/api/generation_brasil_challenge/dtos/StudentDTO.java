package com.generation.api.generation_brasil_challenge.dtos;

import com.generation.api.generation_brasil_challenge.model.Student;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;

    @NotBlank(message = "Required field")
    private String name;
    @Positive(message = "Age must be positive")
    private Long age;
    @DecimalMin(value = "0.0", inclusive = true, message = "The value must be at least 0")
    @DecimalMax(value = "10.0", inclusive = true, message = "The maximum value of the field is 10")
    private Double firstGrade;
    @DecimalMin(value = "0.0",  message = "The value must be at least 0")
    @DecimalMax(value = "10.0", message = "The maximum value of the field is 10")
    private Double secondGrade;
    @NotBlank(message = "Required field")
    private String teacherName;
    @NotBlank(message = "Required field")
    private String room;

    public StudentDTO(Student entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.firstGrade = entity.getFirstGrade();
        this.secondGrade = entity.getSecondGrade();
        this.teacherName = entity.getTeacherName();
        this.room = entity.getRoom();
    }
}

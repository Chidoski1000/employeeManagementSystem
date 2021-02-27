package com.example.employeemanagement2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@MappedSuperclass
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private LocalDateTime timeCreated;
    @UpdateTimestamp
    private LocalDateTime updateTime;
    private String firstname;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    //    private Gender gender;
//    private Level level;
    private String gender;
    private String level;

    @OneToMany
    private List<myLeave> leave;

    @OneToMany
    private List<Salary> salaries;

    @OneToMany
    private List<Attendance> attendances;



//    enum Gender{
//        MALE,FEMALE
//    }
//    enum Level{
//        JUNIOR_STAFF,SENIOR_STAFF,SUPERVISOR,ASST_MANAGER,MANAGER
//    }
}

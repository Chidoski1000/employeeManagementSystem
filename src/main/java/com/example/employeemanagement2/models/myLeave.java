package com.example.employeemanagement2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class myLeave {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    private Date date;
    @CreationTimestamp
    private LocalDateTime date;
    private String leaveDescripton;
    private boolean leaveStatus;

    @ManyToOne
    @JoinColumn(name = ("employee_id"))
    private Employee employee;
}

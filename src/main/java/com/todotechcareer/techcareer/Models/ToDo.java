package com.todotechcareer.techcareer.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "todo_table")
@NoArgsConstructor
@Entity
@Getter
@Setter
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content",nullable = false)
    @NotBlank(message = "Content cannot be blank")
    private String content;

    @Column(name = "status",columnDefinition = "boolean default false")
    private boolean status;




}

package io.red.relatoriospoc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    @Column(name = "employee_id")
    Long id;
    @Column(name = "employee_name")
    String name;

    @Column(name = "employee_role")
    String role;

    @Column(name = "employee_lane")
    String lane;


    public Employee() {
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getLane() {
        return lane;
    }
}

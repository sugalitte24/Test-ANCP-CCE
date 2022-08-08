package com.test.ancp.cce.model;

import com.test.ancp.cce.model.abstracts.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "users")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {

    private String name;
    private String lastName;
    private String email;
    private String password;
}

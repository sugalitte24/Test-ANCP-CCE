package com.test.ancp.cce.model;

import com.test.ancp.cce.model.abstracts.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "products")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Products extends BaseModel {

    public String name;
    public Long amount;
    public Double price;
}

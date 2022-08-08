package com.test.ancp.cce.model;

import com.test.ancp.cce.model.abstracts.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "shopping_car")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCar extends BaseModel {
    public LocalDateTime dateOrder;
    public Double total;

}

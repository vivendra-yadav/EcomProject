package com.scaler.ecom.representingInheritance.tableperclass;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instructor extends User {
    private String specialization;
}
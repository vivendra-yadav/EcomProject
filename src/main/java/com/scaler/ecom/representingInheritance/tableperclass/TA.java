package com.scaler.ecom.representingInheritance.tableperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TA extends User {
    private int numberOfSessions;
    private double avgRating;
}
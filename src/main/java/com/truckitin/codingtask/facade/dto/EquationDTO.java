package com.truckitin.codingtask.facade.dto;

import com.truckitin.codingtask.model.Equation;

public class EquationDTO {
    private String equation;

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public static Equation toEquation(final EquationDTO dto) {
        final Equation e = new Equation();
        e.setEquation(dto.getEquation());
        return e;
    }
}

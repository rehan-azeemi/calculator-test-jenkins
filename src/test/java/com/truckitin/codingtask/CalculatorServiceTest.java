package com.truckitin.codingtask;

import com.truckitin.codingtask.model.Equation;
import com.truckitin.codingtask.model.Result;
import com.truckitin.codingtask.service.CalculatorService;
import com.truckitin.codingtask.service.exception.InvalidEquationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {
    @InjectMocks
    CalculatorService calculatorService;

    @Test
    public void testEquationTestCase(){
        Result result = calculatorService.compute(setUpEquationData());
        Assertions.assertEquals(result.getResult(),-16);
        Assertions.assertEquals(result.getPostFix(),"4572+-*");
    }

    @Test
    public void testInvalidEquationExceptionTestCase(){
        Equation equation = setUpInvalidEquationData();
        Assertions.
                assertThrows(InvalidEquationException.class,
                        ()->calculatorService.compute(equation));
    }

    public Equation setUpEquationData(){
        Equation equation = new Equation();
        equation.setEquation("4*(5-(7+2))");
        return equation;
    }

    public Equation setUpInvalidEquationData(){
        Equation equation = new Equation();
        equation.setEquation("(3+(7+9))_5???");
        return equation;
    }
}

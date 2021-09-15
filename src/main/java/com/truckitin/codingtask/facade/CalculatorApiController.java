package com.truckitin.codingtask.facade;

import com.truckitin.codingtask.facade.dto.EquationDTO;
import com.truckitin.codingtask.facade.dto.ResultDTO;
import com.truckitin.codingtask.model.Equation;
import com.truckitin.codingtask.model.Result;
import com.truckitin.codingtask.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class CalculatorApiController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping(value = "/compute",produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<ResultDTO> compute(@Valid @RequestBody final EquationDTO dto) {
        Equation equation = EquationDTO.toEquation(dto);
        Result result = calculatorService.compute(equation);
        return ResponseEntity.status(HttpStatus.OK).body(ResultDTO.fromResult(result));
    }
}

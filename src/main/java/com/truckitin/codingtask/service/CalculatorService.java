package com.truckitin.codingtask.service;

import com.truckitin.codingtask.model.Equation;
import com.truckitin.codingtask.model.Result;
import com.truckitin.codingtask.service.exception.InvalidEquationException;
import org.springframework.stereotype.Service;
import java.util.Stack;

@Service
public class CalculatorService {

    /*
    * Use to compute the expression
    * @Parameter Equation
    * */
    public Result compute(Equation equation) {
        if(!equation.getEquation().contains("?")){
            Result result = new Result();
            result.setResult(evaluateExpression(equation.getEquation()));
            result.setPostFix(convertInfixToPostfix(equation.getEquation()));
            return result;
        }
        throw new InvalidEquationException("Invalid Equation");
    }

    /*
    * This method is used to evaluate the expression
    */
    private int evaluateExpression(String expression)
    {
        //Convert expression into char array
        char[] tokens = expression.toCharArray();

        Stack<Integer> numbers = new Stack<Integer>();

        Stack<Character> operators = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i] == ' ')
                continue;

            //Check if number is in between 0 to 9 range
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer buffer = new StringBuffer();

                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    buffer.append(tokens[i++]);
                numbers.push(Integer.parseInt(buffer.
                        toString()));
                i--;
            }
            else if (tokens[i] == '(')
                operators.push(tokens[i]);
            else if (tokens[i] == ')')
            {
                while (operators.peek() != '(')
                    numbers.push(operation(operators.pop(),
                            numbers.pop(),
                            numbers.pop()));
                operators.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/')
            {
                while (!operators.empty() && hasPrecedence(tokens[i], operators.peek()))
                    numbers.push(operation(operators.pop(), numbers.pop(), numbers.pop()));
                operators.push(tokens[i]);
            }
        }

        while (!operators.empty())
            numbers.push(operation(operators.pop(),
                    numbers.pop(),
                    numbers.pop()));

        return numbers.pop();
    }

    /*
    * Check the precedence of opertor according to bracket
    * */
    private boolean hasPrecedence(char operator1, char operator2)
    {
        if (operator2 == '(' || operator2 == ')')
            return false;
        if ((operator1 == '*' || operator1 == '/') &&
                (operator2 == '+' || operator2 == '-'))
            return false;
        else
            return true;
    }

    /*
    * Perform the operation on operands
    * */
    private int operation(char operator,
                          int n2, int n1)
    {
        switch (operator)
        {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            case '/':
                if (n2 == 0)
                    throw new InvalidEquationException("Number can't be divide by zero");
                return n1 / n2;
        }
        return 0;
    }

    /*
    * Check the precedence of operator
    * */
    private int precedence(char operator)
    {
        switch (operator)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    /*
    * Use to convert the infix expression into postfix
    * @Parameter String
    * */
    private String convertInfixToPostfix(String expression)
    {
        String result = "";
        Stack<Character> characterStack = new Stack<>();

        for (int i = 0; i<expression.length(); ++i)
        {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c))
                result += c;
            else if (c == '(')
                characterStack.push(c);
            else if (c == ')')
            {
                while (!characterStack.isEmpty() && characterStack.peek() != '(')
                    result += characterStack.pop();

                characterStack.pop();
            }
            else
            {
                while (!characterStack.isEmpty() && precedence(c) <= precedence(characterStack.peek())){
                    result += characterStack.pop();
                }
                characterStack.push(c);
            }

        }

        while (!characterStack.isEmpty()){
            if(characterStack.peek() == '(')
                throw new InvalidEquationException("Invalid Equation");
            result += characterStack.pop();
        }
        return result;
    }

}

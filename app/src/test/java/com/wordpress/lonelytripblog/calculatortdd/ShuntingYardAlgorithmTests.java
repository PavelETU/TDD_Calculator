package com.wordpress.lonelytripblog.calculatortdd;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Павел on 20.01.2018.
 */

public class ShuntingYardAlgorithmTests {

    private Calculator calculator;

    @Before
    public void getReady() {
        calculator = new Calculator();
    }

    @Test
    public void simple_expression() {
        assertEquals("2 2 + ",
                calculator.parseStringByShuntingYardAlgorithm("2+2"));
    }

    @Test
    public void not_so_simple_expression() {
        assertEquals("18 20 35 * + ",
                calculator.parseStringByShuntingYardAlgorithm("18+20*35"));
    }

    @Test
    public void not_so_simple_expression_inv() {
        assertEquals("18 20 * 35 + ",
                calculator.parseStringByShuntingYardAlgorithm("18*20+35"));
    }

    @Test
    public void not_so_simple_expression_with_parenthesis() {
        assertEquals("18 20 35 + * ",
                calculator.parseStringByShuntingYardAlgorithm("18*(20+35)"));
    }

    @Test
    public void not_simple_expression_with_parenthesis() {
        assertEquals("15 7 1 1 + - / 3 * 2 1 1 + + - ",
                calculator.parseStringByShuntingYardAlgorithm("((15/(7-(1+1)))*3)-(2+(1+1))"));
    }

    @Test
    public void simple_expression_with_parenthesis() {
        assertEquals("3 5 + 7 2 - * ",
                calculator.parseStringByShuntingYardAlgorithm("(3+5)*(7-2)"));
    }

}

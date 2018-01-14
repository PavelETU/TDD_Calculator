package com.wordpress.lonelytripblog.calculatortdd;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Павел on 13.01.2018.
 */

public class BaseBlockLogicTests {

    @Test
    public void adding_isCorrect() {
        assertEquals(2 + 2, CalculationBase.add(2, 2), 0);
        assertEquals(-10 + 2, CalculationBase.add(-10, 2), 0);
        assertEquals(2 + 0.5, CalculationBase.add(2, 0.5), 0);
    }

    @Test
    public void subtracting_isCorrect() {
        assertEquals(2 - 2, CalculationBase.subtract(2, 2), 0);
        assertEquals(-10 - 2, CalculationBase.subtract(-10, 2), 0);
        assertEquals(2 - 0.5, CalculationBase.subtract(2, 0.5), 0);
    }

    @Test
    public void multiplication_isCorrect() {
        assertEquals(2 * 2, CalculationBase.multiply(2, 2), 0);
        assertEquals(-10 * 2, CalculationBase.multiply(-10, 2), 0);
        assertEquals(2 * 0.5, CalculationBase.multiply(2, 0.5), 0);
    }

    @Test
    public void division_isCorrect() {
        assertEquals(2 / 2, CalculationBase.divide(2, 2), 0);
        assertEquals(-10 / 2, CalculationBase.divide(-10, 2), 0);
        assertEquals(2 / 0.5, CalculationBase.divide(2, 0.5), 0);
    }

}

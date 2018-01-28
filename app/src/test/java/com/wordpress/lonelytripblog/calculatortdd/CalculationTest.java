package com.wordpress.lonelytripblog.calculatortdd;

import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Павел on 13.01.2018.
 */

public class CalculationTest {

    private Calculator calculator;

    @Before
    public void getReady() {
        calculator = new Calculator();
    }

    @Test
    public void sum_two_one_digit_numbers() {
        assertEquals("6", calculator.calculate("2+2+2"));
    }

    @Test
    public void sum_two_two_digit_numbers() {
        assertEquals("44", calculator.calculate("22+22"));
    }

    @Test
    public void sum_two_three_digit_numbers() {
        assertEquals("444", calculator.calculate("222+222"));
    }

    @Test
    public void mult_two_three_digit_numbers() {
        assertEquals(DecimalFormat.getInstance().format(287.843 * 642.254),
                calculator.calculate("287.843*642.254"));
    }

    @Test
    public void div_two_three_digit_numbers() {
        assertEquals(DecimalFormat.getInstance().format(2),
                calculator.calculate("200/100"));
    }

    @Test
    public void sum_two_with_dec_point_digit_numbers() {
        assertEquals(DecimalFormat.getInstance().format(4.44d),
                calculator.calculate("2.22+2.22"));
    }

    @Test
    public void sum_two_long_with_dec_point_digit_numbers() {
        assertEquals(DecimalFormat.getInstance()
                        .format(565989654648454178955.4845859595894
                                + 8487464684984684987496465484654654.654658496846516549654968465465464649d),
                calculator.calculate("565989654648454178955.4845859595894" +
                        "+8487464684984684987496465484654654.654658496846516549654968465465464649"));
    }

    @Test
    public void sub_two_two_digit_numbers() {
        assertEquals("11", calculator.calculate("22-11"));
    }

    @Test
    public void first_expression() {
        assertEquals(DecimalFormat.getInstance()
                .format(185+987-587.589+4454-87879+54548-87879859-65654.57878+84785-487.5),
                calculator.calculate("185+987-587.589+4454-87879+54548-87879859-65654.57878+84785-487.5"));
    }

    @Test
    public void sub_expression() {
        assertEquals("2",
                calculator.calculate("122.122-10-10-40-15-15.122-15-15"));
    }

    @Test
    public void subtract_two_one_digit_numbers() {
        assertEquals("0", calculator.calculate("2-2"));
    }

    @Test
    public void some_expression() {
        assertEquals("9", calculator.calculate("20/2-1"));
    }

    @Test
    public void some_next_expression() {
        assertEquals("12", calculator.calculate("2+20/2"));
    }

}

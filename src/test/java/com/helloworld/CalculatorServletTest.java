package com.helloworld;

import com.example.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;

import javax.servlet.http.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class CalculatorServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private StringWriter responseWriter;
    private CalculatorServlet calculatorServlet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        responseWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
        calculatorServlet = new CalculatorServlet();
    }

    @Test
    public void testAddition() throws Exception {
        when(request.getParameter("num1")).thenReturn("10");
        when(request.getParameter("num2")).thenReturn("5");
        when(request.getParameter("operation")).thenReturn("add");

        calculatorServlet.doPost(request, response);

        String result = responseWriter.toString();
        assertTrue(result.contains("Result: 15.0"));
    }

    @Test
    public void testSubtraction() throws Exception {
        when(request.getParameter("num1")).thenReturn("10");
        when(request.getParameter("num2")).thenReturn("5");
        when(request.getParameter("operation")).thenReturn("subtract");

        calculatorServlet.doPost(request, response);

        String result = responseWriter.toString();
        assertTrue(result.contains("Result: 5.0"));
    }

    @Test
    public void testMultiplication() throws Exception {
        when(request.getParameter("num1")).thenReturn("10");
        when(request.getParameter("num2")).thenReturn("5");
        when(request.getParameter("operation")).thenReturn("multiply");

        calculatorServlet.doPost(request, response);

        String result = responseWriter.toString();
        assertTrue(result.contains("Result: 50.0"));
    }

    @Test
    public void testDivision() throws Exception {
        when(request.getParameter("num1")).thenReturn("10");
        when(request.getParameter("num2")).thenReturn("5");
        when(request.getParameter("operation")).thenReturn("divide");

        calculatorServlet.doPost(request, response);

        String result = responseWriter.toString();
        assertTrue(result.contains("Result: 2.0"));
    }

    @Test
    public void testDivisionByZero() throws Exception {
        when(request.getParameter("num1")).thenReturn("10");
        when(request.getParameter("num2")).thenReturn("0");
        when(request.getParameter("operation")).thenReturn("divide");

        calculatorServlet.doPost(request, response);

        String result = responseWriter.toString();
        assertTrue(result.contains("Error: Division by zero is not allowed."));
    }

    @Test
    public void testInvalidOperation() throws Exception {
        when(request.getParameter("num1")).thenReturn("10");
        when(request.getParameter("num2")).thenReturn("5");
        when(request.getParameter("operation")).thenReturn("invalid");

        calculatorServlet.doPost(request, response);

        String result = responseWriter.toString();
        assertTrue(result.contains("Error: Invalid operation."));
    }

    @Test
    public void testInvalidInput() throws Exception {
        when(request.getParameter("num1")).thenReturn("abc");
        when(request.getParameter("num2")).thenReturn("5");
        when(request.getParameter("operation")).thenReturn("add");

        calculatorServlet.doPost(request, response);

        String result = responseWriter.toString();
        assertTrue(result.contains("Error: Invalid input. Please enter valid numbers."));
    }
}
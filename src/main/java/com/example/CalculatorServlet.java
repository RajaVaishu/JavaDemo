package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CalculatorServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Get input parameters
            double num1 = Double.parseDouble(request.getParameter("num1"));
            double num2 = Double.parseDouble(request.getParameter("num2"));
            String operation = request.getParameter("operation");
            double result = 0;

            // Perform the calculation based on the selected operation
            switch (operation) {
                case "add":
                    result = num1 + num2;
                    break;
                case "subtract":
                    result = num1 - num2;
                    break;
                case "multiply":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        out.println("<html><body><h3>Error: Division by zero is not allowed.</h3></body></html>");
                        return;
                    }
                    break;
                default:
                    out.println("<html><body><h3>Error: Invalid operation.</h3></body></html>");
                    return;
            }

            // Display the result
            out.println("<html><body>");
            out.println("<h3>Result: " + result + "</h3>");
            out.println("</body></html>");
        } catch (NumberFormatException e) {
            out.println("<html><body><h3>Error: Invalid input. Please enter valid numbers.</h3></body></html>");
        } catch (Exception e) {
            out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
        } finally {
            out.close();
        }
    }
}

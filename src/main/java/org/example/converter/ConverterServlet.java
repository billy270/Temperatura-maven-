package org.example.converter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConverterServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String SRC_VALUE = "srcValue";
    private static final String SRC_UNIT = "srcUnit";
    private static final String RES_UNIT = "resUnit";

    @EJB
    TemperatureConverter converter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        request.setAttribute("result", null);
        request.setAttribute(SRC_VALUE, 0.0);
        request.setAttribute(SRC_UNIT, "CELSIUS");
        request.setAttribute(RES_UNIT, "KELVIN");
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double value = Double.parseDouble(request.getParameter(SRC_VALUE));
        String srcUnit = request.getParameter(SRC_UNIT);
        String resUnit = request.getParameter(RES_UNIT);
        double result = converter.convert(value, srcUnit, resUnit);

        response.setContentType(CONTENT_TYPE);
        request.setAttribute("result", String.valueOf(result));
        request.setAttribute(SRC_VALUE, value);
        request.setAttribute(SRC_UNIT, srcUnit);
        request.setAttribute(RES_UNIT, resUnit);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}

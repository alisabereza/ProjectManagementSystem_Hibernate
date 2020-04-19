package com.project.management.controller;

import com.project.management.config.ErrorMessage;
import com.project.management.domain.Company;
import com.project.management.domainDAO.CompanyDAO;
import com.project.management.services.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

@WebServlet(urlPatterns = "/company/*")
public class CompanyServlet extends HttpServlet {
    private CompanyDAO companyDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        companyDAO = new CompanyDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        System.out.println(action);
        if (action.startsWith("/findCompany")) {
            req.getRequestDispatcher("/view/find_company.jsp").forward(req, resp);

        }
        if (action.startsWith("/createCompany")) {
            req.getRequestDispatcher("/view/create_company.jsp").forward(req, resp);
        }
        if (action.startsWith("/allCompanies")) {
            List<Company> companies = companyDAO.getAll();
            System.out.println(companies.get(0));
            req.setAttribute("companies", companies);
            System.out.println(companies.get(1));
            req.getRequestDispatcher("/view/all_companies.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        if (action.startsWith("/createCompany")) {
            Company company = mapCompany(req);
            List<ErrorMessage> errorMessages = validateCompany(company);
            if (!errorMessages.isEmpty()) {
                req.setAttribute("errors", errorMessages);
                req.getRequestDispatcher("/view/create_company.jsp").forward(req, resp);
            } else {
                companyDAO.create(company);
                req.setAttribute("message", "New Company created: " + company.getName());
                req.getRequestDispatcher("/view/create_company.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/findCompany")) {
            final String id = req.getParameter("id").trim();
            final Company company = companyDAO.read(Integer.parseInt(id));
            if (company == null) {
                req.setAttribute("message", "Company not found");
                req.getRequestDispatcher("/view/find_company.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", String.format("Company found: ID=%s, title=%s, start date=%s", company.getId(), company.getName(), company.getStartDate()));
                req.getRequestDispatcher("/view/find_company.jsp").forward(req, resp);
            }
        }

    }

    private Company mapCompany(HttpServletRequest req) {
        final String companyName = req.getParameter("title").trim();
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MMM-yyyy")
                .toFormatter(Locale.ENGLISH);
        final LocalDate startDate = LocalDate.parse(req.getParameter("date"), df);
        return new Company(companyName, startDate);
    }

    private List<ErrorMessage> validateCompany(Company company) {
        final List<ErrorMessage> errorMessages = Validator.validateEntity(company);
        final Company persistentCompany = companyDAO.findByName(company.getName());
        if (Objects.nonNull(persistentCompany) && !persistentCompany.getName().isEmpty()) {
            errorMessages.add(new ErrorMessage("", "Company with this title already exists"));
        }
        return errorMessages;
    }


    private String getAction(HttpServletRequest req) {
        final String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }
}


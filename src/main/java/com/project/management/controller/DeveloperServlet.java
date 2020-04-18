package com.project.management.controller;

import com.project.management.config.ErrorMessage;
import com.project.management.domain.Company;
import com.project.management.domain.Developer;
import com.project.management.domain.DeveloperGender;
import com.project.management.domainDAO.CompanyDAO;
import com.project.management.domainDAO.DeveloperDAO;
import com.project.management.services.CompanyService;
import com.project.management.services.DeveloperService;
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
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@WebServlet(urlPatterns = "/developer/*")
public class DeveloperServlet extends HttpServlet {
    private DeveloperDAO developerDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        developerDAO = new DeveloperDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        System.out.println(action);
        if (action.startsWith("/findDeveloper")) {
            req.getRequestDispatcher("/view/find_developer.jsp").forward(req, resp);

        } else if (action.startsWith("/createDeveloper")) {
            req.setAttribute("genders", DeveloperGender.values());
            req.getRequestDispatcher("/view/create_developer.jsp").forward(req, resp);
        }
        else if (action.startsWith("/updateDeveloper")) {
            req.getRequestDispatcher("/view/developerView/update_developer.jsp").forward(req, resp);
        }
        else if (action.startsWith("/deleteDeveloper")) {
            req.getRequestDispatcher("/view/developerView/delete_developer.jsp").forward(req, resp);
        }
        else if (action.startsWith("/showAllDevelopers")) {
            List<Developer> developers = developerDAO.getAll();
            req.setAttribute("developers", developers);
            req.getRequestDispatcher("/view/developerView/show_developers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        if (action.startsWith("/createDeveloper")) {
            Developer developer = mapDeveloper(req);
            List<ErrorMessage> errorMessages = validateDeveloper(developer);
            if (!errorMessages.isEmpty()) {
                req.setAttribute("errors", errorMessages);
                req.getRequestDispatcher("/view/create_developer.jsp").forward(req, resp);
            } else {
                developerDAO.create(developer);
                req.setAttribute("genders", DeveloperGender.values());
                req.setAttribute("message", "Developer created: " + developer.getName());
                req.getRequestDispatcher("/view/create_developer.jsp").forward(req, resp);
            }
        }
        if (action.startsWith("/updateDeveloper")) {
            Developer developer = developerDAO.read(Integer.parseInt(req.getParameter("id")));
            int newSalary = Integer.parseInt(req.getParameter("salary"));
            developer.setSalary(newSalary);
            developerDAO.update(developer);
            req.getRequestDispatcher("/view/developerView/developer_updated.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteDeveloper")) {
            int id = Integer.parseInt(req.getParameter("id"));
            developerDAO.delete(developerDAO.read(id));
            req.getRequestDispatcher("/view/developerView/developer_deleted.jsp").forward(req, resp);
        }

    }

    private Developer mapDeveloper(HttpServletRequest req){
        final String name = req.getParameter("name").trim();
        final int age = Integer.parseInt(req.getParameter("age"));
        DeveloperGender gender= DeveloperGender.valueOf(req.getParameter("gender"));
        final int salary = Integer.parseInt(req.getParameter("salary"));
        Company company = new CompanyDAO().findByName(req.getParameter("companyName"));
        Developer developer = new Developer(name, age, gender, salary, company);
        return developer;
    }

    private List<ErrorMessage> validateDeveloper (Developer developer){
        final List<ErrorMessage> errorMessages = Validator.validateEntity(developer);
        final Developer persistentDeveloper = developerDAO.findByName(developer.getName());
        if (Objects.nonNull(persistentDeveloper) && !persistentDeveloper.getName().isEmpty()) {
            errorMessages.add(new ErrorMessage("", "Developer with this name already exists"));
        }
        return errorMessages;
    }


    private String getAction (HttpServletRequest req){
        final String requestURI = req.getRequestURI();
        String requestPathWithServletContext = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletContext.length());
    }
}
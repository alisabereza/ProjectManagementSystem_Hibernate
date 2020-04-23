package com.project.management.controller;

import com.project.management.utils.ErrorMessage;
import com.project.management.model.company.Company;
import com.project.management.model.developer.Developer;
import com.project.management.model.developer.DeveloperGender;
import com.project.management.model.company.CompanyDAO;
import com.project.management.model.developer.DeveloperDAO;
import com.project.management.utils.EntityValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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
        if (action.startsWith("/findDeveloper")) {
            req.getRequestDispatcher("/view/developer/find_developer.jsp").forward(req, resp);

        }  if (action.startsWith("/createDeveloper")) {
            req.setAttribute("genders", DeveloperGender.values());
            req.getRequestDispatcher("/view/developer/create_developer.jsp").forward(req, resp);
        }
         if (action.startsWith("/updateDeveloper")) {
            req.getRequestDispatcher("/view/developer/update_developer.jsp").forward(req, resp);
        }
         if (action.startsWith("/deleteDeveloper")) {
            req.getRequestDispatcher("/view/developer/delete_developer.jsp").forward(req, resp);
        }
         if (action.startsWith("/allDevelopers")) {
            List<Developer> developers = developerDAO.getAll();
            req.setAttribute("developers", developers);
            req.getRequestDispatcher("/view/developer/all_developers.jsp").forward(req, resp);
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
            } else {
                developerDAO.create(developer);
                req.setAttribute("genders", DeveloperGender.values());
                req.setAttribute("message", "Developer created: " + developer.getName());
            }
            req.getRequestDispatcher("/view/developer/create_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateDeveloper")) {
            Developer developer = developerDAO.read(Integer.parseInt(req.getParameter("id")));
            if (developer==null)
            {
                req.setAttribute("message", "Developer not found");
            }
            else {
            int newSalary = Integer.parseInt(req.getParameter("salary"));
            developer.setSalary(newSalary);
            developerDAO.update(developer);
            req.setAttribute("message", String.format("Developer updated: ID=%s, name=%s, age=%s, salary=%s, company=%s",developer.getId(), developer.getName(), developer.getAge(), developer.getSalary(), developer.getCompany().getName()));
}
            req.getRequestDispatcher("/view/developer/update_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteDeveloper")) {
            int id = Integer.parseInt(req.getParameter("id"));
            if (developerDAO.read(id)==null)

                {
                    req.setAttribute("message", "Developer not found");
                }
            else {developerDAO.delete(developerDAO.read(id));
            req.setAttribute("message", String.format("Developer with ID=%s deleted", id));}
            req.getRequestDispatcher("/view/developer/delete_developer.jsp").forward(req, resp);
        }
        if (action.startsWith("/findDeveloper")) {
            final String id = req.getParameter("id").trim();
            final Developer developer = developerDAO.read(Integer.parseInt(id));
            if (developer==null)
            {
                req.setAttribute("message", "Developer not found");
            }
            else {
                req.setAttribute("message", String.format("Developer found: ID=%s, name=%s, age=%s, salary=%s, company=%s",developer.getId(), developer.getName(), developer.getAge(), developer.getSalary(), developer.getCompany().getName()));
            }
            req.getRequestDispatcher("/view/developer/find_developer.jsp").forward(req,resp);
        }

    }

    private Developer mapDeveloper(HttpServletRequest req){
        final String name = req.getParameter("name").trim();
        final int age = Integer.parseInt(req.getParameter("age"));
        DeveloperGender gender= DeveloperGender.valueOf(req.getParameter("gender"));
        final int salary = Integer.parseInt(req.getParameter("salary"));
        Company company = new CompanyDAO().findByName(req.getParameter("companyName"));
        return new Developer(name, age, gender, salary, company);
    }

    private List<ErrorMessage> validateDeveloper (Developer developer){
        final List<ErrorMessage> errorMessages = EntityValidator.validateEntity(developer);
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

package com.project.management.controller;

import com.project.management.model.company.Company;
import com.project.management.model.company.CompanyDAO;
import com.project.management.model.project.Project;
import com.project.management.model.project.ProjectDAO;
import com.project.management.utils.ActionValidator;
import com.project.management.utils.EntityValidator;
import com.project.management.utils.ErrorMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@WebServlet(urlPatterns = "/project/*")
public class ProjectServlet extends HttpServlet {
    private ProjectDAO projectDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        projectDAO = new ProjectDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = ActionValidator.getAction(req);
        if (action.startsWith("/findProject")) {
            req.getRequestDispatcher("/view/project/find_project.jsp").forward(req, resp);

        }
        if (action.startsWith("/createProject")) {
            req.setAttribute("message", "Date Format is: dd-mmm-yyyy");
            req.getRequestDispatcher("/view/project/create_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateProject")) {
            req.getRequestDispatcher("/view/project/update_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteProject")) {
            req.getRequestDispatcher("/view/project/delete_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/allProjects")) {
            List<Project> projects = projectDAO.getAll();
            req.setAttribute("projects", projects);
            req.getRequestDispatcher("/view/project/all_projects.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = ActionValidator.getAction(req);
        if (action.startsWith("/createProject")) {
            try {
                Project project = mapProject(req);
                List<ErrorMessage> errorMessages = validateProject(project);
                if (!errorMessages.isEmpty()) {
                    req.setAttribute("errors", errorMessages);
                } else {
                    projectDAO.create(project);
                    req.setAttribute("message", "Project created: " + project);
                }
            } catch (IllegalArgumentException e) {
                req.setAttribute("message", e.getMessage());
            }
            req.getRequestDispatcher("/view/project/create_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/updateProject")) {
            Project project = projectDAO.read(Integer.parseInt(req.getParameter("id")));
            if (project == null) {
                req.setAttribute("message", "Project not found");
            } else {
                int newCost = Integer.parseInt(req.getParameter("cost"));
                project.setCost(newCost);
                projectDAO.update(project);
                req.setAttribute("message", String.format("Project updated: %s", project));
            }
            req.getRequestDispatcher("/view/project/update_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/deleteProject")) {
            int id = Integer.parseInt(req.getParameter("id"));
            if (projectDAO.read(id) == null) {
                req.setAttribute("message", "Project not found");
            } else {
                projectDAO.delete(projectDAO.read(id));
                req.setAttribute("message", String.format("Project with ID=%s deleted", id));
            }
            req.getRequestDispatcher("/view/project/delete_project.jsp").forward(req, resp);
        }
        if (action.startsWith("/findProject")) {
            final String id = req.getParameter("id").trim();
            final Project project = projectDAO.read(Integer.parseInt(id));
            if (project == null) {
                req.setAttribute("message", "Project not found");
            } else {
                req.setAttribute("message", String.format("Project found: %s", project));
            }
            req.getRequestDispatcher("/view/project/find_project.jsp").forward(req, resp);
        }

    }

    private Project mapProject(HttpServletRequest req) {
        try {
            final String name = req.getParameter("name").trim();
            DateTimeFormatter df = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("dd-MMM-yyyy")
                    .toFormatter(Locale.ENGLISH);
            final LocalDate startDate = LocalDate.parse(req.getParameter("date"), df);
            final int cost = Integer.parseInt(req.getParameter("cost"));
            Company company = new CompanyDAO().findByName(req.getParameter("companyName"));
            return new Project(name, startDate, cost, company);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date Format should be: dd-mmm-yyyy");
        }
    }

    private List<ErrorMessage> validateProject(Project project) {
        final List<ErrorMessage> errorMessages = EntityValidator.validateEntity(project);
        final Project persistentProject = projectDAO.findByName(project.getName());
        if (Objects.nonNull(persistentProject) && !persistentProject.getName().isEmpty()) {
            errorMessages.add(new ErrorMessage("", "Project with this name already exists"));
        }
        return errorMessages;
    }
}
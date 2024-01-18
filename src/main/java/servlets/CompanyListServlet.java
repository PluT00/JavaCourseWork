package servlets;

import model.Company;
import services.CompanyService;
import services.db.CompanyDAO;
import services.db.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CompanyListServlet", urlPatterns = "/companies")
public class CompanyListServlet extends HttpServlet {
    private final CompanyService companyService = new CompanyService(new CompanyDAO(new DatabaseService()));

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            List<Company> companies = companyService.getAllCompanies();
            request.setAttribute("companies", companies);
            request.getRequestDispatcher("pages/companyList.jsp").forward(request, response);
        } else {
            response.sendRedirect("/signin");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String name = request.getParameter("name");
            Company company = new Company(name);
            companyService.addCompany(company);

            response.sendRedirect(request.getContextPath() + "/companies");
        } else {
            response.sendRedirect("/signin");
        }
    }
}


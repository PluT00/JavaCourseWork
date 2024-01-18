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

@WebServlet("/company_details")
public class CompanyDetailsServlet extends HttpServlet {
    private final CompanyService companyService = new CompanyService(new CompanyDAO(new DatabaseService()));

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Company company = companyService.getCompanyById(id);
                request.setAttribute("company", company);
                request.getRequestDispatcher("pages/companyDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/companies");
            }
        } else {
            response.sendRedirect("/signin");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                String action = request.getParameter("action");
                if ("update".equals(action)) {
                    String name = request.getParameter("name");
                    Company company = new Company(id, name);
                    companyService.updateCompany(company);
                } else if ("delete".equals(action)) {
                    companyService.deleteCompany(id);
                }
                response.sendRedirect(request.getContextPath() + "/companies");
            } else {
                response.sendRedirect(request.getContextPath() + "/companies");
            }
        } else {
            response.sendRedirect("/signin");
        }
    }
}
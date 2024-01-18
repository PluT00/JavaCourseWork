package services;

import model.Company;
import services.db.CompanyDAO;

import java.util.List;

public class CompanyService {
    private CompanyDAO companyDAO;

    public CompanyService(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    public void addCompany(Company company) {
        companyDAO.addCompany(company);
    }

    public List<Company> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }

    public void updateCompany(Company company) {
        companyDAO.updateCompany(company);
    }

    public void deleteCompany(int companyId) {
        companyDAO.deleteCompany(companyId);
    }

    public Company getCompanyById(int companyId) {
        return companyDAO.getCompanyById(companyId);
    }

    public Company getCompanyByName(String name) {
        return companyDAO.getCompanyByName(name);
    }
}

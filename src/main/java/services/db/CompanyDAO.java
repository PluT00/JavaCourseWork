package services.db;

import model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {
    private DatabaseService databaseService;

    public CompanyDAO(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void addCompany(Company company) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "INSERT INTO companies (name) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, company.getName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM companies";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Company company = new Company();
                        company.setId(resultSet.getInt("id"));
                        company.setName(resultSet.getString("name"));
                        companies.add(company);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    public void updateCompany(Company company) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "UPDATE companies SET name=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, company.getName());
                preparedStatement.setInt(2, company.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCompany(int companyId) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "DELETE FROM companies WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, companyId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company getCompanyById(int companyId) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM companies WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, companyId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Company company = new Company();
                        company.setId(resultSet.getInt("id"));
                        company.setName(resultSet.getString("name"));
                        return company;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Company getCompanyByName(String name) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM companies WHERE name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Company company = new Company();
                        company.setId(resultSet.getInt("id"));
                        company.setName(resultSet.getString("name"));
                        return company;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


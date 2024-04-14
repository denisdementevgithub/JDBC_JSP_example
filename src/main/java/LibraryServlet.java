import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;
@WebServlet("/library")
public class LibraryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQl driver is not found");
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library_db",
                    "postgres", "admin");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from books");
            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("NAMEBOOK: " + resultSet.getString(2) + ", ");
                result.append(resultSet.getString(2) + ", ");
                result.append("AUTHORNAME: ");
                result.append(resultSet.getString(3) + ", ");
                result.append("QUANTITY: ");
                result.append(resultSet.getString(4) + ", ");
                result.append("PRICE: ");
                result.append(resultSet.getString(5));
                result.append("<br />");
            }
            req.setAttribute("result", result);
            getServletContext().getRequestDispatcher("/libraryJSP.jsp").forward(req, resp);
            statement.close();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

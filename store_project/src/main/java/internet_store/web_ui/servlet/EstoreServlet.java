package internet_store.web_ui.servlet;

import internet_store.web_ui.configuration.SessionConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebServlet(name = "estore", urlPatterns = {"/"}, loadOnStartup = 1)
public class EstoreServlet extends HttpServlet {
    @Autowired
    private SessionConfiguration sessionConfiguration;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        sessionConfiguration.sessionConfigurator(request, response);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index");
        requestDispatcher.forward(request,response);
        response.flushBuffer();
    }
}
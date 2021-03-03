package internet_store.web_ui.servlet;

import internet_store.web_ui.configuration.SessionConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebServlet(name = "client", urlPatterns = {"/estore/add_client_for_cart"}, loadOnStartup = 2)
public class ClientServlet extends HttpServlet {
    @Autowired
    private SessionConfiguration sessionConfiguration;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sessionConfiguration.sessionConfigurator(request, response);

        response.sendRedirect("/estore/cart_add_client");
        response.flushBuffer();
    }
}
package internet_store.web_ui.configuration;

import internet_store.core.request.session.RestoreRequest;
import internet_store.core.service.session.SessionService;
import internet_store.web_ui.cookies.Cookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionConfiguration {
    @Autowired
    private SessionService sessionService;

    public void sessionConfigurator(HttpServletRequest request, HttpServletResponse response) {
        Cookies cookies = new Cookies(request, response);

        sessionService.setSessionId(cookies.getSessionId());

        sessionService.restoreClientLastSession(new RestoreRequest(cookies.getSessionId()));
    }
}
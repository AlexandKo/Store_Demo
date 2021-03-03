package internet_store.web_ui.cookies;

import lombok.Getter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class Cookies {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private Cookie[] cookiesFromClient;
    @Getter
    private String sessionId = "";

    public Cookies(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        getCookiesFromClient();
    }

    private void getCookiesFromClient() {
        cookiesFromClient = request.getCookies();
        findSessionId();
    }

    private void findSessionId() {
        if (cookiesFromClient != null) {
            Optional<Cookie> optionalCookie = Arrays.stream(cookiesFromClient).filter(c -> c.getName()
                    .equals("clientId")).findFirst();
            optionalCookie.ifPresentOrElse(cookie -> sessionId = cookie.getValue(),
                    () -> sessionId = "");
        }
        setSessionId();
    }

    private void setSessionId() {
        final int STORE_COOKIE_ONE_DAY = 24 * 60 * 60;

        if (sessionId.equals("")) {
            sessionId = request.getSession().getId();

            Cookie cookie = new Cookie("clientId", sessionId);
            cookie.setMaxAge(STORE_COOKIE_ONE_DAY);

            response.addCookie(cookie);
        }
    }
}
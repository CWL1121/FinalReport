package ethci.lab.r3feditorback.Security;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RestFullAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final String LOGGED_IN = "logged_in";
    private final String USER_TYPE = "user_type";
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        String account = authentication.getName();
        addSameSiteCookieAttribute(resp);
        Collection collection = authentication.getAuthorities();
        String authority = collection.iterator().next().toString();
        HttpSession session = req.getSession();
        session.setAttribute(LOGGED_IN, account);
        session.setAttribute(USER_TYPE, authority);
        Map<String, String> result = new HashMap<>();
        result.put("authorized", "Authorized");
        result.put("account", account);
        result.put("authority", authority);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        resp.setStatus(200);
        ObjectMapper om = new ObjectMapper();
        out.write(om.writeValueAsString(result));
        out.flush();
        out.close();
    }

    private void addSameSiteCookieAttribute(HttpServletResponse response) {
        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader = true;
        // there can be multiple Set-Cookie attributes
        for (String header : headers) {
            if (firstHeader) {
                response.setHeader(HttpHeaders.SET_COOKIE,
                        String.format("%s; %s", header, "SameSite=none; Secure"));
                firstHeader = false;
                continue;
            }
            response.addHeader(HttpHeaders.SET_COOKIE,
                    String.format("%s; %s", header, "SameSite=none; Secure"));
        }
    }
}

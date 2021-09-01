package security.view.model.processor.issue;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class HomeController {

    @Produces(MediaType.TEXT_HTML)
    @Get("/good")
    @View("home")
    Map<String, Object> worksForMe(Principal principal) {
        Map<String, Object> result = new HashMap<>();
        result.put("principal", principal);
        return result;
    }

    @Produces(MediaType.TEXT_HTML)
    @Get("/bad")
    @View("home")
    AuthInfo doesntWorkForMe(Principal principal) {
        var result = new AuthInfo();
        result.setName(principal.getName());
        return result;
    }

    @Introspected
    static class AuthInfo {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}

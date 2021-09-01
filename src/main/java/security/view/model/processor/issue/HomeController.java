package security.view.model.processor.issue;

import com.fasterxml.jackson.databind.util.BeanUtil;
import io.micronaut.core.beans.BeanMap;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.inject.beans.visitor.MappedSuperClassIntrospectionMapper;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class HomeController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/index")
    String index(Principal principal) {
        return principal.getName();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/good")
    Map<String, Object> worksForMe(Principal principal) {
        Map<String, Object> result = new HashMap<>();
        result.put("principal", principal);
        return result;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/bad")
    Principal doesntWorkForMe(Principal principal) {
        return principal;
    }

}

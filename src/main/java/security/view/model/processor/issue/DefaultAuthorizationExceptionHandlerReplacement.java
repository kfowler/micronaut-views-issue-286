package security.view.model.processor.issue;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.security.authentication.AuthorizationException;
import io.micronaut.security.authentication.DefaultAuthorizationExceptionHandler;

import jakarta.inject.Singleton;

@Singleton
@Replaces(DefaultAuthorizationExceptionHandler.class)
public class DefaultAuthorizationExceptionHandlerReplacement extends DefaultAuthorizationExceptionHandler {

    @Override
    protected MutableHttpResponse<?> httpResponseWithStatus(HttpRequest request, AuthorizationException exception) {
        if (exception.isForbidden()) {
            return HttpResponse.status(HttpStatus.FORBIDDEN);
        }
        return HttpResponse.status(HttpStatus.UNAUTHORIZED)
                .header("WWW-Authenticate", "Basic realm=\"Micronaut Guide\"");
    }
}

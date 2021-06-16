package webflux.junit5.test.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class DocumentRouter {
  @Bean
  public RouterFunction<ServerResponse> route(DocumentHandler documentHandler) {
    return RouterFunctions
      .route(RequestPredicates.GET("/documents").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
        documentHandler::documents);
  }
}

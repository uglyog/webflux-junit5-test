package webflux.junit5.test.provider;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DocumentHandler {
  public Mono<ServerResponse> documents(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue("Hello, Spring!"));
  }
}

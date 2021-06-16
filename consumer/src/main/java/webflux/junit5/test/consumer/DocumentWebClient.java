package webflux.junit5.test.consumer;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class DocumentWebClient {
  public DocumentList getDocuments(String baseUrl) {
    WebClient client = WebClient.create(baseUrl);

    Mono<ClientResponse> result = client.get()
      .uri("/documents")
      .accept(MediaType.APPLICATION_JSON)
      .exchange();

    return result.flatMap(res -> res.bodyToMono(DocumentList.class)).block();
  }
}

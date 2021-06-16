package webflux.junit5.test.consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "DocumentProvider", pactVersion = PactSpecVersion.V4)
public class ConsumerPactTest {

  @Pact(provider="DocumentProvider", consumer="DocumentConsumer")
  public V4Pact createPact(PactDslWithProvider builder) {
    return builder
      .given("There are at least two documents")
      .uponReceiving("a request for the documents")
      .path("/documents")
      .method("GET")
      .willRespondWith()
      .status(200)
      .body(new PactDslJsonBody()
        .eachLike("documents")
          .stringType("name", "doco_001")
          .stringMatcher("id", "\\w{3}\\d{12}", "AAA123456789012")
      )
      .toPact(V4Pact.class);
  }

  @Test
  void testFetchDocumentList(MockServer provider) {
    DocumentWebClient client = new DocumentWebClient();
    DocumentList list = client.getDocuments(provider.getUrl());
    assertEquals(1, list.getDocuments().size());
    assertEquals("doco_001", list.getDocuments().get(0).getName());
  }
}

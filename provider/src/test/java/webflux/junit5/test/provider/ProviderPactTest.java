package webflux.junit5.test.provider;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import au.com.dius.pact.provider.spring.junit5.WebFluxTarget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("DocumentProvider")
@PactFolder("pacts")
public class ProviderPactTest {
  @LocalServerPort
  int port;

  @Autowired
  DocumentRouter documentRouter;

  @Autowired
  DocumentHandler documentHandler;

  @BeforeEach
  void setup(PactVerificationContext context) {
    context.setTarget(new WebFluxTarget(documentRouter.route(documentHandler)));
  }

  @TestTemplate
  @ExtendWith(PactVerificationSpringProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State("There are at least two documents")
  void setupDocuments() {
    List<Document> documents = documentHandler.getDocumentList().getDocuments();
    documents.add(new Document("TestDoco", "ABC123412341234"));
    documents.add(new Document("TestDoco2", "ABC123456789012"));
  }
}

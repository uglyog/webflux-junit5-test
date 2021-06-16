package webflux.junit5.test.consumer;

import java.util.ArrayList;
import java.util.List;

public class DocumentList {
  private List<Document> documents = new ArrayList<>();

  public DocumentList(List<Document> documents) {
    this.documents = documents;
  }

  public DocumentList() {
  }

  public List<Document> getDocuments() {
    return documents;
  }

  public void setDocuments(List<Document> documents) {
    this.documents = documents;
  }
}

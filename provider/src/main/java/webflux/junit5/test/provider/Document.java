package webflux.junit5.test.provider;

public class Document {
  private String name;
  private String id;

  public Document(String name, String id) {
    this.name = name;
    this.id = id;
  }

  public Document() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
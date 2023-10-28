package data;

public enum CourseTitleData {
  APACHEKAFKA("Apache Kafka"),
  DEVREL("DevRel");

  private final String name;

  CourseTitleData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

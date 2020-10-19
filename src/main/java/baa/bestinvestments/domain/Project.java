package baa.bestinvestments.domain;

import baa.bestinvestments.domain.exceptions.IllegalStatusException;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Project {

  private final ClientId clientId;
  private final Date endDate;
  private final String name;
  private final ProjectStatus status;

  private ProjectReference reference;
  private ProjectManagerId projectManagerId;

  private Project(ClientId clientId,
                  Date endDate,
                  String name,
                  ProjectStatus projectStatus) {
    this.clientId = clientId;
    this.endDate = endDate;
    this.name = name;
    this.status = projectStatus;
  }

  public static Project draft(ClientId clientId,
                              Date endDate,
                              String name) {
    return new Project(clientId, endDate, name, ProjectStatus.DRAFT);
  }

  public void start(ProjectManagerId projectManagerId) {
    if (status != ProjectStatus.DRAFT) {
      throw new IllegalStatusException("Status must be DRAFT");
    }
    this.projectManagerId = projectManagerId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
      .append("clientId", clientId)
      .append("endDate", endDate)
      .append("name", name)
      .append("status", status)
      .append("reference", reference)
      .toString();
  }

}

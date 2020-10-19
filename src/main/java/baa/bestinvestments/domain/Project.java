package baa.bestinvestments.domain;

import baa.bestinvestments.domain.exceptions.IllegalStatusException;
import baa.bestinvestments.domain.exceptions.UnknownSpecialistException;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static baa.bestinvestments.domain.ProjectReference.generateReference;

public class Project {

  private final ClientId clientId;
  private final LocalDate endDate;
  private final String name;
  private final ProjectReference reference;

  private ProjectStatus status;
  private ProjectManagerId projectManagerId;
  private Set<SpecialistId> specialistIds = new HashSet<SpecialistId>();
  private Set<SpecialistId> approvedSpecialistIds = new HashSet<SpecialistId>();

  private Project(ClientId clientId, LocalDate endDate, String name, ProjectStatus projectStatus) {
    this.clientId = clientId;
    this.endDate = endDate;
    this.name = name;
    this.status = projectStatus;
    this.reference = generateReference();
  }

  public static Project draft(ClientId clientId, LocalDate endDate, String name) {
    return new Project(clientId, endDate, name, ProjectStatus.DRAFT);
    // TODO event : ProjectDraftedEvent()
  }

  public void start(ProjectManagerId projectManagerId) {
    if (status != ProjectStatus.DRAFT) {
      throw new IllegalStatusException("Status must be DRAFT");
    }
    this.projectManagerId = projectManagerId;
    this.status = ProjectStatus.ACTIVE;
  }

  public void addSpecialist(SpecialistId specialistId) {
    if (status != ProjectStatus.ACTIVE) {
      throw new IllegalStatusException("Status must be ACTIVE");
    }
    specialistIds.add(specialistId);
    // TODO event : SpecialistAddedEvent()
  }

  public void approveSpecialist(SpecialistId specialistId) {
    if (status != ProjectStatus.ACTIVE) {
      throw new IllegalStatusException("Status must be ACTIVE");
    }
    if (!specialistIds.contains(specialistId)) {
      throw new UnknownSpecialistException();
    }
    specialistIds.remove(specialistId);
    approvedSpecialistIds.add(specialistId);
    // TODO event : SpecialistApprovedEvent()
  }

  public boolean isDraft() {
    return status == ProjectStatus.DRAFT;
  }

  public boolean isActive() {
    return status == ProjectStatus.ACTIVE;
  }

  public Set<SpecialistId> specialists() {
    return specialistIds;
  }

  public Set<SpecialistId> approvedSpecialists() {
    return approvedSpecialistIds;
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

package baa.bestinvestments.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

  @Test
  void shouldDraftProject() {

    // Given

    // When
    Project project = Project.draft(new ClientId("1234"), LocalDate.now(), "Test Project");

    // Then
    assertThat(project.isDraft()).isTrue();
  }

  @Test
  void shouldStartProject() {

    // Given
    Project project = Project.draft(new ClientId("1234"), LocalDate.now(), "Test Project");

    // When
    project.start(new ProjectManagerId("1001"));

    // Then
    assertThat(project.isActive()).isTrue();
  }

  @Test
  void shouldAddProjectSpecialist() {

    // Given
    Project project = Project.draft(new ClientId("1234"), LocalDate.now(), "Test Project");
    project.start(new ProjectManagerId("1001"));
    assertThat(project.specialists()).isEmpty();

    // When
    project.addSpecialist(new SpecialistId("100"));

    // Then
    assertThat(project.specialists()).containsExactly(new SpecialistId("100"));
  }

  @Test
  void shouldApproveProjectSpecialist() {

    // Given
    Project project = Project.draft(new ClientId("1234"), LocalDate.now(), "Test Project");
    project.start(new ProjectManagerId("1001"));
    project.addSpecialist(new SpecialistId("100"));

    // When
    project.approveSpecialist(new SpecialistId("100"));

    // Then
    assertThat(project.specialists()).isEmpty();
    assertThat(project.approvedSpecialists()).containsExactly(new SpecialistId("100"));
  }

}

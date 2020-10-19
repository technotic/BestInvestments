package baa.bestinvestments.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ProjectReference {

  private final String value;

  private ProjectReference(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return new org.apache.commons.lang3.builder.ToStringBuilder(this)
      .append("value", value)
      .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    ProjectReference clientId = (ProjectReference) o;

    return new EqualsBuilder()
      .append(value, clientId.value)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
      .append(value)
      .toHashCode();
  }
}

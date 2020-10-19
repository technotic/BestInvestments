package baa.bestinvestments.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class ClientId {

  private final String value;

  private ClientId(String value) {
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

    ClientId clientId = (ClientId) o;

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

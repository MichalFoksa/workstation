package net.michalfoksa.demo.workshop.workstation.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Describes how a request or response was created.
 */
@ApiModel(description = "Describes how a request or response was created.")
@Validated

public class MessageContext   {
  @JsonProperty("correlationId")
  private String correlationId = null;

  public MessageContext correlationId(String correlationId) {
    this.correlationId = correlationId;
    return this;
  }

  /**
   * Client generated ID to correlate all service requests in one business operation (transaction).
   * @return correlationId
  **/
  @ApiModelProperty(value = "Client generated ID to correlate all service requests in one business operation (transaction).")


  public String getCorrelationId() {
    return correlationId;
  }

  public void setCorrelationId(String correlationId) {
    this.correlationId = correlationId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageContext messageContext = (MessageContext) o;
    return Objects.equals(this.correlationId, messageContext.correlationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(correlationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageContext {\n");
    
    sb.append("    correlationId: ").append(toIndentedString(correlationId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


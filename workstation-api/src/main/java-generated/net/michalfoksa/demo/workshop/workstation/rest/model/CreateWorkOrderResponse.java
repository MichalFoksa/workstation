package net.michalfoksa.demo.workshop.workstation.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.michalfoksa.demo.workshop.workstation.rest.model.MessageContext;
import net.michalfoksa.demo.workshop.workstation.rest.model.Workstation;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateWorkOrderResponse
 */

public class CreateWorkOrderResponse   {
  @JsonProperty("body")
  private Workstation body = null;

  @JsonProperty("messageContext")
  private MessageContext messageContext = null;

  @JsonProperty("runtimeContext")
  @Valid
  private Map<String, Object> runtimeContext = null;

  public CreateWorkOrderResponse body(Workstation body) {
    this.body = body;
    return this;
  }

  /**
   * Get body
   * @return body
  */
  @ApiModelProperty(value = "")

  @Valid

  public Workstation getBody() {
    return body;
  }

  public void setBody(Workstation body) {
    this.body = body;
  }

  public CreateWorkOrderResponse messageContext(MessageContext messageContext) {
    this.messageContext = messageContext;
    return this;
  }

  /**
   * Get messageContext
   * @return messageContext
  */
  @ApiModelProperty(value = "")

  @Valid

  public MessageContext getMessageContext() {
    return messageContext;
  }

  public void setMessageContext(MessageContext messageContext) {
    this.messageContext = messageContext;
  }

  public CreateWorkOrderResponse runtimeContext(Map<String, Object> runtimeContext) {
    this.runtimeContext = runtimeContext;
    return this;
  }

  public CreateWorkOrderResponse putRuntimeContextItem(String key, Object runtimeContextItem) {
    if (this.runtimeContext == null) {
      this.runtimeContext = new HashMap<>();
    }
    this.runtimeContext.put(key, runtimeContextItem);
    return this;
  }

  /**
   * Describes runtime environment of a pod or container executing the request.
   * @return runtimeContext
  */
  @ApiModelProperty(value = "Describes runtime environment of a pod or container executing the request.")


  public Map<String, Object> getRuntimeContext() {
    return runtimeContext;
  }

  public void setRuntimeContext(Map<String, Object> runtimeContext) {
    this.runtimeContext = runtimeContext;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateWorkOrderResponse createWorkOrderResponse = (CreateWorkOrderResponse) o;
    return Objects.equals(this.body, createWorkOrderResponse.body) &&
        Objects.equals(this.messageContext, createWorkOrderResponse.messageContext) &&
        Objects.equals(this.runtimeContext, createWorkOrderResponse.runtimeContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(body, messageContext, runtimeContext);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateWorkOrderResponse {\n");
    
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    messageContext: ").append(toIndentedString(messageContext)).append("\n");
    sb.append("    runtimeContext: ").append(toIndentedString(runtimeContext)).append("\n");
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


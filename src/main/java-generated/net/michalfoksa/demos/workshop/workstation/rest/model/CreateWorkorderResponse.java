package net.michalfoksa.demos.workshop.workstation.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.michalfoksa.demos.workshop.workstation.rest.model.MessageContext;
import net.michalfoksa.demos.workshop.workstation.rest.model.Workstation;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateWorkorderResponse
 */
@Validated

public class CreateWorkorderResponse   {
  @JsonProperty("body")
  private Workstation body = null;

  @JsonProperty("messageContext")
  private MessageContext messageContext = null;

  @JsonProperty("runtimeContext")
  @Valid
  private Map<String, Object> runtimeContext = null;

  public CreateWorkorderResponse body(Workstation body) {
    this.body = body;
    return this;
  }

  /**
   * Get body
   * @return body
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Workstation getBody() {
    return body;
  }

  public void setBody(Workstation body) {
    this.body = body;
  }

  public CreateWorkorderResponse messageContext(MessageContext messageContext) {
    this.messageContext = messageContext;
    return this;
  }

  /**
   * Get messageContext
   * @return messageContext
  **/
  @ApiModelProperty(value = "")

  @Valid

  public MessageContext getMessageContext() {
    return messageContext;
  }

  public void setMessageContext(MessageContext messageContext) {
    this.messageContext = messageContext;
  }

  public CreateWorkorderResponse runtimeContext(Map<String, Object> runtimeContext) {
    this.runtimeContext = runtimeContext;
    return this;
  }

  public CreateWorkorderResponse putRuntimeContextItem(String key, Object runtimeContextItem) {
    if (this.runtimeContext == null) {
      this.runtimeContext = new HashMap<>();
    }
    this.runtimeContext.put(key, runtimeContextItem);
    return this;
  }

  /**
   * Describes runtime environment of a pod or container executing the request.
   * @return runtimeContext
  **/
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
    CreateWorkorderResponse createWorkorderResponse = (CreateWorkorderResponse) o;
    return Objects.equals(this.body, createWorkorderResponse.body) &&
        Objects.equals(this.messageContext, createWorkorderResponse.messageContext) &&
        Objects.equals(this.runtimeContext, createWorkorderResponse.runtimeContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(body, messageContext, runtimeContext);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateWorkorderResponse {\n");
    
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


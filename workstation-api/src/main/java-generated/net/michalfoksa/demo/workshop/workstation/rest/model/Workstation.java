package net.michalfoksa.demo.workshop.workstation.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Workstation
 */
@ApiModel(description = "Workstation")

public class Workstation   {
  @JsonProperty("name")
  private String name;

  @JsonProperty("url")
  private String url;

  @JsonProperty("parameters")
  @Valid
  private Map<String, String> parameters = new HashMap<>();

  public Workstation name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the workstation
   * @return name
  */
  @ApiModelProperty(example = "paintshop", required = true, value = "Name of the workstation")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Workstation url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Optional URL of the workstation. Is is appended with `/works`. When present it is used to contact the workstation instead of other resolver/discovery mechanism. Url parameter is usefull in local development.
   * @return url
  */
  @ApiModelProperty(example = "http://paintshop:8080", value = "Optional URL of the workstation. Is is appended with `/works`. When present it is used to contact the workstation instead of other resolver/discovery mechanism. Url parameter is usefull in local development.")


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Workstation parameters(Map<String, String> parameters) {
    this.parameters = parameters;
    return this;
  }

  public Workstation putParametersItem(String key, String parametersItem) {
    this.parameters.put(key, parametersItem);
    return this;
  }

  /**
   * Free named parameters. They do not have any pupose, just to pass some data through assebly line workstations.
   * @return parameters
  */
  @ApiModelProperty(example = "{\"engine\":\"V8\",\"transmission\":\"automatic\",\"bodyType\":\"sedan\"}", required = true, value = "Free named parameters. They do not have any pupose, just to pass some data through assebly line workstations.")
  @NotNull


  public Map<String, String> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Workstation workstation = (Workstation) o;
    return Objects.equals(this.name, workstation.name) &&
        Objects.equals(this.url, workstation.url) &&
        Objects.equals(this.parameters, workstation.parameters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, url, parameters);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Workstation {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
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


package net.michalfoksa.demo.workshop.workstation.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.michalfoksa.demo.workshop.workstation.rest.model.Workstation;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Work order for a workstation.
 */
@ApiModel(description = "Work order for a workstation.")

public class WorkOrder   {
  @JsonProperty("workstationName")
  private String workstationName;

  @JsonProperty("parameters")
  @Valid
  private Map<String, String> parameters = new HashMap<>();

  @JsonProperty("nextStations")
  @Valid
  private List<Workstation> nextStations = new ArrayList<>();

  public WorkOrder workstationName(String workstationName) {
    this.workstationName = workstationName;
    return this;
  }

  /**
   * Name of the workstation which is supposed to execute the work.
   * @return workstationName
  */
  @ApiModelProperty(example = "bodyshop", required = true, value = "Name of the workstation which is supposed to execute the work.")
  @NotNull


  public String getWorkstationName() {
    return workstationName;
  }

  public void setWorkstationName(String workstationName) {
    this.workstationName = workstationName;
  }

  public WorkOrder parameters(Map<String, String> parameters) {
    this.parameters = parameters;
    return this;
  }

  public WorkOrder putParametersItem(String key, String parametersItem) {
    this.parameters.put(key, parametersItem);
    return this;
  }

  /**
   * Free named parameters. Their only pupose is to to pass some data through assebly line workstations.
   * @return parameters
  */
  @ApiModelProperty(example = "{\"engine\":\"V8\",\"transmission\":\"automatic\",\"bodyType\":\"sedan\"}", required = true, value = "Free named parameters. Their only pupose is to to pass some data through assebly line workstations.")
  @NotNull


  public Map<String, String> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, String> parameters) {
    this.parameters = parameters;
  }

  public WorkOrder nextStations(List<Workstation> nextStations) {
    this.nextStations = nextStations;
    return this;
  }

  public WorkOrder addNextStationsItem(Workstation nextStationsItem) {
    this.nextStations.add(nextStationsItem);
    return this;
  }

  /**
   * Get nextStations
   * @return nextStations
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<Workstation> getNextStations() {
    return nextStations;
  }

  public void setNextStations(List<Workstation> nextStations) {
    this.nextStations = nextStations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkOrder workOrder = (WorkOrder) o;
    return Objects.equals(this.workstationName, workOrder.workstationName) &&
        Objects.equals(this.parameters, workOrder.parameters) &&
        Objects.equals(this.nextStations, workOrder.nextStations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(workstationName, parameters, nextStations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WorkOrder {\n");
    
    sb.append("    workstationName: ").append(toIndentedString(workstationName)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
    sb.append("    nextStations: ").append(toIndentedString(nextStations)).append("\n");
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


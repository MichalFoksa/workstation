package net.michalfoksa.demos.workshop.workstation.rest.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A user task
 */
@ApiModel(description = "A user task")
@Validated

public class Task   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("completed")
  private Boolean completed = null;

  public Task id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * The unique identifier of the given task
   * @return id
  **/
  @ApiModelProperty(value = "The unique identifier of the given task")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Task description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the task
   * @return description
  **/
  @ApiModelProperty(value = "Description of the task")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Task completed(Boolean completed) {
    this.completed = completed;
    return this;
  }

  /**
   * Indication if the task was completed or not
   * @return completed
  **/
  @ApiModelProperty(value = "Indication if the task was completed or not")


  public Boolean isCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task task = (Task) o;
    return Objects.equals(this.id, task.id) &&
        Objects.equals(this.description, task.description) &&
        Objects.equals(this.completed, task.completed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, completed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Task {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    completed: ").append(toIndentedString(completed)).append("\n");
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


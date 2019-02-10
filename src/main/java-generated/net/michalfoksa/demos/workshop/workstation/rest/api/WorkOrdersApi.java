/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package net.michalfoksa.demos.workshop.workstation.rest.api;

import net.michalfoksa.demos.workshop.workstation.rest.model.CreateWorkOrderResponse;
import net.michalfoksa.demos.workshop.workstation.rest.model.WorkOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Api(value = "WorkOrders", description = "the WorkOrders API")
public interface WorkOrdersApi {

    Logger log = LoggerFactory.getLogger(WorkOrdersApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "", nickname = "createWorkOrder", notes = "Create a work order", response = CreateWorkOrderResponse.class, responseContainer = "List", tags={ "workOrders", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = CreateWorkOrderResponse.class, responseContainer = "List") })
    @RequestMapping(value = "/workorders",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<List<CreateWorkOrderResponse>> createWorkOrder(@ApiParam(value = "Work order for a workstation" ,required=true )  @Valid @RequestBody WorkOrder workOrder) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"runtimeContext\" : {    \"key\" : \"{}\"  },  \"messageContext\" : {    \"correlationId\" : \"correlationId\"  },  \"body\" : {    \"name\" : \"name\",    \"parameters\" : {      \"key\" : \"parameters\"    },    \"url\" : \"url\"  }}, {  \"runtimeContext\" : {    \"key\" : \"{}\"  },  \"messageContext\" : {    \"correlationId\" : \"correlationId\"  },  \"body\" : {    \"name\" : \"name\",    \"parameters\" : {      \"key\" : \"parameters\"    },    \"url\" : \"url\"  }} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default WorkOrdersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}

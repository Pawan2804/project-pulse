package com.proj_management.project_pulse.web;

import com.proj_management.project_pulse.domain.Project;
import com.proj_management.project_pulse.services.MapValidErrorService;
import com.proj_management.project_pulse.services.ProjectServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/project")
public class ProjectController {
    @Autowired
    private ProjectServices projectServices;
    @Autowired
    private MapValidErrorService mapValidErrorService;
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        ResponseEntity<?> errorMap = mapValidErrorService.MapValidationService(result);
        if (errorMap!=null) return errorMap;
        Project pro = projectServices.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(pro, HttpStatus.CREATED);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project = projectServices.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectServices.findAllProject();
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectServices.deleteProjectByIdentifier(projectId.toUpperCase());
        return new ResponseEntity<String>("Project Id: "+projectId+ " Deleted",HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable String projectId, @RequestBody Project project){
        Project res = projectServices.updateProject(project,projectId);
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }


}

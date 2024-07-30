package com.proj_management.project_pulse.web;

import com.proj_management.project_pulse.domain.Project;
import com.proj_management.project_pulse.services.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/project")
public class ProjectController {
    @Autowired
    private ProjectServices projectServices;
    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project){
        Project pro = projectServices.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(pro, HttpStatus.CREATED);
    }


}

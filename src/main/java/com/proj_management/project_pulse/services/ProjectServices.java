package com.proj_management.project_pulse.services;

import com.proj_management.project_pulse.domain.Project;
import com.proj_management.project_pulse.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServices {
    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project){
        return projectRepository.save(project);
    }
}

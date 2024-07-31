package com.proj_management.project_pulse.services;

import com.proj_management.project_pulse.domain.Project;
import com.proj_management.project_pulse.exception.ProjectIdException;
import com.proj_management.project_pulse.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServices {
    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception e){
            throw new ProjectIdException("Project Id: "+project.getProjectIdentifier().toUpperCase()+" Already Exist");
        }

    }
    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project==null){
            throw new ProjectIdException("Project Id: "+projectId+"Does Not Exist ");
        }
        return project;
    }
    public Iterable<Project> findAllProject(){
    return projectRepository.findAll();
    }
    public void deleteProjectByIdentifier(String projectid){
        Project project = projectRepository.findByProjectIdentifier(projectid);
        if(project==null){
            throw new ProjectIdException("Project Id: "+projectid+"Does Not Exist ");
        }
        projectRepository.delete(project);
    }
    public Project updateProject(Project project, String projectId){
        Project projectUpdate = projectRepository.findByProjectIdentifier(projectId);
        if(project==null){
            throw new ProjectIdException("Project Id: "+projectId+"Does Not Exist ");
        }
        projectUpdate.setProjectName(project.getProjectName());
        projectUpdate.setDescription(project.getDescription());
        projectUpdate.setEndDate(project.getEndDate());
        projectUpdate.setStartDate(project.getStartDate());
        return projectRepository.save(projectUpdate);
    }
}

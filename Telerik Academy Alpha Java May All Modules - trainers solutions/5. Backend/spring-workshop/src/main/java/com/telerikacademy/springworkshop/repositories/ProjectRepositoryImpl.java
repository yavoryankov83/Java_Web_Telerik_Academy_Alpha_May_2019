package com.telerikacademy.springworkshop.repositories;

import com.telerikacademy.springworkshop.models.Project;
import com.telerikacademy.springworkshop.repositories.contracts.Repository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository("ProjectRepository")
public class ProjectRepositoryImpl implements Repository<Project> {
    private List<Project> projects;

    public ProjectRepositoryImpl() {
        InitialData data = new InitialData();
        projects = data.getProjects();
    }

    @Override
    public void create(Project project) {
        projects.add(project);
    }

    @Override
    public List<Project> getAll() {
        return new ArrayList<>(projects);
    }

    @Override
    public Project getById(int id) {
        return projects.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Project with id %d not exist.", id)));
    }

    @Override
    public void update(int id, Project project) {
        Project projectToUpdate = getById(id);
        projectToUpdate.setName(project.getName());
    }

    @Override
    public void delete(int id) {
        Project projectToDelete = getById(id);
        projects.remove(projectToDelete);
    }
}

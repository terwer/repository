package com.terwergreen.repository;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Jar项目
 *
 * @author Terwer
 * @Version 1.0
 * @Date 2018/12/4 14:32
 * @Description
 **/
public class JarProject extends Project {
    public JarProject(Project project, String fileLocation,String repositoryFileLocation) {
        super(project.getGroupId(), project.getArtifactId(), project.getVersion(), PackageTypeEnum.JAR);
        this.fileLocation = fileLocation;
        this.repositoryFileLocation = repositoryFileLocation;
    }

    public JarProject(String groupId, String artifactId, String version) {
        super(groupId, artifactId, version, PackageTypeEnum.JAR);
    }

}

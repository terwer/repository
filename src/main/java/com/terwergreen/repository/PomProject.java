package com.terwergreen.repository;

import java.nio.file.Paths;

/**
 * POM项目
 *
 * @author Terwer
 * @Version 1.0
 * @Date 2018/12/4 14:31
 * @Description
 **/
public class PomProject extends Project {
    public PomProject(Project project, String fileLocation,String repositoryFileLocation) {
        super(project.getGroupId(), project.getArtifactId(), project.getVersion(), project.getPackaging());
        this.fileLocation = fileLocation;
        this.pomFileLocation = Paths.get(this.fileLocation).getParent().getParent().toString()+"\\pom-parent.xml";
        this.repositoryFileLocation = repositoryFileLocation;
    }

    public PomProject(String groupId, String artifactId, String version) {
        super(groupId, artifactId, version, PackageTypeEnum.POM);
    }
}

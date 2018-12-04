package com.terwergreen.repository;

/**
 * POM项目
 *
 * @author Terwer
 * @Version 1.0
 * @Date 2018/12/4 14:31
 * @Description
 **/
public class PomProject extends Project {
    public PomProject(Project project, String fileLocation) {
        super(project.getGroupId(), project.getArtifactId(), project.getVersion(), PackageTypeEnum.POM);
        this.fileLocation = fileLocation;
    }

    public PomProject(String groupId, String artifactId, String version) {
        super(groupId, artifactId, version, PackageTypeEnum.POM);
    }
}

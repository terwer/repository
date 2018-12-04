package com.terwergreen.repository;

/**
 * War项目
 *
 * @author Terwer
 * @Version 1.0
 * @Date 2018/12/4 14:32
 * @Description
 **/
public class WarProject extends Project {
    public WarProject(Project project, String fileLocation) {
        super(project.getGroupId(), project.getArtifactId(), project.getVersion(), PackageTypeEnum.WAR);
        this.fileLocation = fileLocation;
    }

    public WarProject(String groupId, String artifactId, String version) {
        super(groupId, artifactId, version, PackageTypeEnum.WAR);
    }
}

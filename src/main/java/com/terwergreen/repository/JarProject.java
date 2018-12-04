package com.terwergreen.repository;

/**
 * Jar项目
 *
 * @author Terwer
 * @Version 1.0
 * @Date 2018/12/4 14:32
 * @Description
 **/
public class JarProject extends Project {
    public JarProject(Project project,String fileLocation) {
        super(project.getGroupId(), project.getArtifactId(), project.getVersion(), PackageTypeEnum.JAR);
        this.fileLocation = fileLocation;
    }

    public JarProject(String groupId, String artifactId, String version) {
        super(groupId, artifactId, version, PackageTypeEnum.JAR);
    }

}

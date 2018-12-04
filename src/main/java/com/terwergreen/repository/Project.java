package com.terwergreen.repository;

/**
 * maven项目
 *
 * @author Terwer
 * @Version 1.0
 * @Date 2018/12/4 14:25
 * @Description
 **/
public class Project {
    private String groupId = "com.terwergreen";
    private String artifactId = "repository";
    private String version = "1.0.0";
    private PackageTypeEnum packaging = PackageTypeEnum.POM;
    protected String fileLocation = "";

    public Project(String groupId, String artifactId, String version, PackageTypeEnum packaging) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.packaging = packaging;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public PackageTypeEnum getPackaging() {
        return packaging;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    @Override
    public String toString() {
        return groupId + "." + artifactId + "-" + version + "." + packaging;
    }
}

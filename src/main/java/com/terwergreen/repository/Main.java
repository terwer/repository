package com.terwergreen.repository;

/**
 * maven仓库
 *
 * @author Terwer
 * @Version 1.0
 * @Date 2018/12/4 14:24
 * @Description
 **/
public class Main {
    public static void main(String[] args) {
        String command = "";

        // 默认为Jar项目
        Project project = new Project("com.terwergreen", "bugucms-plugin-container", "1.0.0", PackageTypeEnum.JAR);

        // POM项目
        Project pomProject = new PomProject(project, "C:\\Users\\Terwer\\IdeaProjects\\shared-plugin-interfaces\\target\\shared-plugin-interfaces-4.1.8.jar", ".");
        command = install(pomProject);

        System.out.println("==================");
        System.out.println("=======" + project.getPackaging().name().toLowerCase() + "========");
        System.out.println(project.toString());
        System.out.println(command);
        System.out.println("==================\n");

        // Jar项目
        Project jarProject = new JarProject(project, "C:\\Users\\Terwer\\IdeaProjects\\shared-plugin-interfaces\\target\\shared-plugin-interfaces-4.1.8.jar", ".");
        command = install(jarProject);

        System.out.println("==================");
        System.out.println("=======" + project.getPackaging().name().toLowerCase() + "========");
        System.out.println(project.toString());
        System.out.println(command);
        System.out.println("==================\n");

        // War项目
        // Project warProject = new WarProject(project, "./test.war", ".");
        // command = install(warProject);

        // System.out.println("==================");
        // System.out.println("=======" + project.getPackaging().name().toLowerCase() + "========");
        // System.out.println(project.toString());
        // System.out.println(command);
        // System.out.println("==================\n");
    }

    public static String install(Project project) {
        String command = String.format("mvn install:install-file -DgroupId=%s -DartifactId=%s -Dversion=%s -Dfile=%s -Dpackaging=%s -DgeneratePom=true -DlocalRepositoryPath=%s -DcreateChecksum=true", project.getGroupId(), project.getArtifactId(), project.getVersion(), project.getFileLocation(), project.getPackaging().name().toLowerCase(), project.getRepositoryFileLocation());
        if (null != project.pomFileLocation && !project.pomFileLocation.equals("")) {
            command = String.format("mvn install:install-file -DgroupId=%s -DartifactId=%s -Dversion=%s -Dfile=%s -Dpackaging=%s -DgeneratePom=true -DpomFile=%s -DlocalRepositoryPath=%s -DcreateChecksum=true", project.getGroupId(), project.getArtifactId(), project.getVersion(), project.getFileLocation(), project.getPackaging().name().toLowerCase(), project.getPomFileLocation(), project.getRepositoryFileLocation());
        }
        return command;
    }
}

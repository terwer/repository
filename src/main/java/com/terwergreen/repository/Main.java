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
        // 默认为Jar项目
        Project project = new Project("com.terwergreen", "bugucms-plugin-container", "1.0.0", PackageTypeEnum.JAR);

        // POM项目
        Project pomProject = new PomProject(project, "./test.pom");
        install(pomProject);

        // Jar项目
        Project jarProject = new JarProject(project, "./test.jar");
        install(jarProject);

        // War项目
        Project warProject = new WarProject(project, "./test.war");
        install(warProject);
    }

    public static String install(Project project) {
        String command = String.format("mvn install:install-file -DgroupId=%s -DartifactId=%s -Dversion=%s -Dfile=%s -Dpackaging=%s -DgeneratePom=true -DlocalRepositoryPath=. -DcreateChecksum=true", project.getGroupId(), project.getArtifactId(), project.getVersion(), project.getFileLocation(), project.getPackaging().name().toLowerCase());
        System.out.println("==================");
        System.out.println("=======" + project.getPackaging().name().toLowerCase() + "========");
        System.out.println(project.toString());
        System.out.println(command);
        System.out.println("==================\n");
        return command;
    }
}

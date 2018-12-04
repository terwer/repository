package com.terwergreen.repository.app;

import com.terwergreen.repository.JarProject;
import com.terwergreen.repository.PackageTypeEnum;
import com.terwergreen.repository.PomProject;
import com.terwergreen.repository.Project;
import com.terwergreen.repository.WarProject;
import com.terwergreen.repository.lib.Toast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.terwergreen.repository.Main.install;

public class Controller {
    Logger logger = Logger.getLogger(this.getClass().getName());

    private Desktop desktop = Desktop.getDesktop();
    final FileChooser fileChooser = new FileChooser();
    private String fileLocation;

    @FXML
    private Button btnOpenFile;
    @FXML
    private Button btnCommand;
    @FXML
    private Button btnRun;
    @FXML
    private TextField txtGroupId;
    @FXML
    private TextField txtArtifactId;
    @FXML
    private TextField txtVersion;
    @FXML
    private TextField txtPackaging;
    @FXML
    private TextArea textAreaInfo;


    public Controller() {
        System.out.println("init");
    }

    @FXML
    private void initialize() {
        System.out.println("initialize");
        btnOpenFile.setMaxWidth(Double.MAX_VALUE);
        textAreaInfo.setWrapText(true);
    }

    @FXML
    protected void openFile(ActionEvent event) {
        Node node = (Node) event.getSource();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, file.getName());
            // openFileFromDesktop(file);
            loadInfo(file);
        }
    }

    private void loadInfo(File file) {
        try {
            //设置文件路径
            fileLocation = file.getAbsolutePath();
            textAreaInfo.setText("文件路径：" + fileLocation);

            String fileName = file.getName();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
            txtPackaging.setText(fileExt);

            String packageName = fileName.substring(0, fileName.lastIndexOf("-"));
            txtArtifactId.setText(packageName);
            String version = fileName.replace("." + fileExt, "").substring(fileName.lastIndexOf("-") + 1);
            txtVersion.setText(version);
            logger.log(Level.INFO, "文件读取成功");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "读取文件名出错", e);
        }
    }

    private void openFileFromDesktop(File file) {
        try {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "准备打开文件");
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "文件打开出错", ex);
        }
    }

    @FXML
    protected void command(ActionEvent event) {
        btnRun.setDisable(false);
        Node node = (Node) event.getSource();

        if (txtArtifactId.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setContentText("必须填写模块名");
            alert.showAndWait();
            return;
        }

        if (fileLocation == null || fileLocation.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setContentText("文件信息读取失败，请重新选择");
            alert.showAndWait();
            return;
        }

        //运行mvn命令
        URL url = getClass().getClassLoader().getResource(".");
        Path path = Paths.get(url.getPath().substring(1)).getParent().getParent();
        System.out.println("命令运行在：" + path.toUri().toString());

        // 默认为Jar项目
        Project project = new Project(txtGroupId.getText(), txtArtifactId.getText(), txtVersion.getText(), PackageTypeEnum.JAR);
        if (txtPackaging.getText().equals(PackageTypeEnum.JAR.name().toLowerCase())) {
            // Jar项目
            project = new JarProject(project, fileLocation);
        } else if (txtPackaging.getText().equals(PackageTypeEnum.POM.name().toLowerCase())) {
            // POM项目
            project = new PomProject(project, fileLocation);
        } else if (txtPackaging.getText().equals(PackageTypeEnum.WAR.name().toLowerCase())) {
            // War项目
            project = new WarProject(project, fileLocation);
        }

        String command = install(project);
        textAreaInfo.setText(command);

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(command);
        clipboard.setContent(content);

        String toastMsg = "命令已复制到剪切板";
        int toastMsgTime = 1500; //1.5 seconds
        int fadeInTime = 500; //0.5 seconds
        int fadeOutTime = 500; //0.5 seconds
        Toast.makeText((Stage) node.getScene().getWindow(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);

        System.out.println("命令：");
        System.out.println(command);
    }

    @FXML
    protected void run(ActionEvent event) {
        btnRun.setDisable(true);
        if (textAreaInfo.getText() == null || !textAreaInfo.getText().contains("mvn")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("提示");
            alert.setContentText("命令生成失败，请重新生成再执行");
            alert.showAndWait();
            btnRun.setDisable(false);
            return;
        }
        System.out.println("执行");
    }
}

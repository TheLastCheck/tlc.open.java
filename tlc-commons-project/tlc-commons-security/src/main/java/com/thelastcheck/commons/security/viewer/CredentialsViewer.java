/*******************************************************************************
 * The Last Check, LLC
 * 9499 Grove Trail Lane
 * Germantown, TN 38139
 *
 * Unauthorized distribution, adaptation or use may be subject to civil and
 * criminal penalties.
 *
 * Copyright (c) 2015, The Last Check, LLC, All rights reserved.
 ******************************************************************************/

package com.thelastcheck.commons.security.viewer;

import com.thelastcheck.commons.security.Credentials;
import com.thelastcheck.commons.security.CredentialsReader;
import com.thelastcheck.commons.security.CredentialsWriter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class CredentialsViewer extends Application {

    private static String[] commandLineArgs;

    private Text messageText;
    private TextField userName;
    private PasswordField password1;
    private PasswordField password2;
    private TextField filename;
    private TextField fileLocation;

    public static void main(String[] args) {
        commandLineArgs = args;
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Setup Credentials File");
//        primaryStage.initModality(Modality.APPLICATION_MODAL);
        GridPane grid = createGrid();
        int row = 0;
        row = addInputFields(grid, row);
        setDefaultValues();
        row = addButtons(grid, row);
        row = addMessageText(grid, row);
        Scene scene = new Scene(grid, 700, 275);
//        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(closeEventHandler());
        primaryStage.show();
    }

    private EventHandler<WindowEvent> closeEventHandler() {
        return new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
            }
        };
    }

    private void setDefaultValues() {
        String defaultFileLocation = "../etc";
        if (commandLineArgs.length >= 2) {
            if (commandLineArgs[0].equals("-d")) {
                defaultFileLocation = commandLineArgs[1];
            }
        }
        Path path = Paths.get(defaultFileLocation);
        path = path.normalize();
        fileLocation.setText(path.toString());
    }

    private int addMessageText(GridPane grid, int row) {
        messageText = new Text();
        grid.add(messageText, 1, row++);
        return row;
    }

    private int addButtons(GridPane grid, int row) {
        Button exitButton = addExitButton();
        Button writeButton = addWriteButton();
        Button readButton = addReadButton();
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(writeButton, readButton, exitButton);
        grid.add(hbBtn, 1, row++);
        return row;
    }

    private Button addExitButton() {
        Button btn = new Button("Exit");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });
        return btn;
    }

    private Button addWriteButton() {
        Button btn = new Button("Write File");
        btn.setDefaultButton(true);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                messageText.setFill(Color.FIREBRICK);
                messageText.setText("Write to file...");
                if (isValidData()) {
                    writeToFile();
                }

            }
        });
        return btn;
    }

    private Button addReadButton() {
        Button btn = new Button("Read File");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                messageText.setFill(Color.FIREBRICK);
                readFromFile();
            }
        });
        return btn;
    }

    private void writeToFile() {
        String user = System.getProperty("user.name");
        String userdata = "File created by " + user + " on " + new Date();
        Credentials credentials = new Credentials(userName.getText().trim(), password1.getText().trim(), userdata);
        String filename = checkFileSuffix();
        File file = new File(fileLocation.getText().trim(), filename);
        CredentialsWriter writer = new CredentialsWriter(file);
        try {
            writer.write(credentials);
        } catch (Exception e) {
            messageText.setText("Error occurred: " + e.getLocalizedMessage());
            this.filename.requestFocus();
            return;
        }
        messageText.setText("Write complete. " + userdata);
        userName.requestFocus();
    }

    private String checkFileSuffix() {
        String filename = this.filename.getText().trim();
        if (!filename.endsWith(".credentials")) {
            filename = filename + ".credentials";
            this.filename.setText(filename);
        }
        return filename;
    }

    private void readFromFile() {
        String filename = checkFileSuffix();
        File file = new File(fileLocation.getText().trim(), filename);
        try {
            CredentialsReader reader = new CredentialsReader(file);
            Credentials credentials = reader.read();
            userName.setText(credentials.getUsername());
            password1.setText(credentials.getPassword());
            password2.setText(credentials.getPassword());
            messageText.setText(credentials.getUserData());
        } catch (Exception e) {
            messageText.setText("Error occurred: " + e.getLocalizedMessage());
            this.filename.requestFocus();
            return;
        }
        userName.requestFocus();
    }

    private boolean isValidData() {
        if (userName.getText().trim().length() < 1) {
            userName.requestFocus();
            messageText.setText("User name is required");
            return false;
        }
        if (password1.getText().trim().length() < 1) {
            password1.requestFocus();
            messageText.setText("Password is required");
            return false;
        }
        if (!password1.getText().trim().equals(password2.getText())) {
            password1.requestFocus();
            messageText.setText("Passwords are not the same");
            return false;
        }
        if (filename.getText().trim().length() < 1) {
            filename.requestFocus();
            messageText.setText("Filename is required");
            return false;
        }
        if (fileLocation.getText().trim().length() < 1) {
            fileLocation.requestFocus();
            messageText.setText("File location is required");
            return false;
        }
        File file = new File(fileLocation.getText().trim());
        if (!file.exists() || !file.isDirectory()) {
            fileLocation.requestFocus();
            messageText.setText("File location missing or is not a directory");
            return false;
        }
        return true;
    }

    private int addInputFields(GridPane grid, int row) {
        Text scenetitle = new Text("Credentials");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, row++, 2, 1);

        grid.add(new Label("User Name:"), 0, row);
        userName = new TextField();
        userName.setMaxWidth(200);
        grid.add(userName, 1, row++);

        grid.add(new Label("Password:"), 0, row);
        password1 = new PasswordField();
        password1.setMaxWidth(200);
        grid.add(password1, 1, row++);

        grid.add(new Label("Reenter Password:"), 0, row);
        password2 = new PasswordField();
        password2.setMaxWidth(200);
        grid.add(password2, 1, row++);

        grid.add(new Label("Filename:"), 0, row);
        filename = new TextField();
        filename.setMaxWidth(525);
        grid.add(filename, 1, row++);

        grid.add(new Label("File Location:"), 0, row);
        fileLocation = new TextField();
        fileLocation.setPrefWidth(525);
        grid.add(fileLocation, 1, row++);

//        grid.setGridLinesVisible(true);
        return row;
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return grid;
    }
}
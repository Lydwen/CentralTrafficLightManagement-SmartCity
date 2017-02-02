package fr.unice.polytech.al.trafficlight.electriccar.view;

import co.paralleluniverse.galaxy.core.Comm;
import fr.unice.polytech.al.trafficlight.electriccar.provider.CommunicationService;
import fr.unice.polytech.al.trafficlight.electriccar.utils.SpatialDatabase;
import fr.unice.polytech.al.trafficlight.electriccar.utils.SpatialTrafficLight;
import javafx.application.Application;
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

public class Launcher extends Application {

    private SpatialDatabase database;
    private String[] prevTrafficLight;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        prevTrafficLight = new String[2];
        prevTrafficLight[0] = "None";
        prevTrafficLight[1] = "None";
        database = new SpatialDatabase();
        database.updateTrafficLight(new CommunicationService().getTrafficLights());

        primaryStage.setTitle("IHM ElectricCar");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("I'm here");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label longitude = new Label("Longitude :");
        grid.add(longitude, 0, 1);

        TextField longitudeField = new TextField();
        grid.add(longitudeField, 1, 1);

        Label latitude = new Label("Latitude :");
        grid.add(latitude, 0, 2);

        TextField latitudeField = new TextField();
        grid.add(latitudeField, 1, 2);

        Label carId = new Label("Car ID :");
        grid.add(carId, 0, 3);

        TextField carIdField = new TextField();
        grid.add(carIdField, 1, 3);

        Button ImHereBtn = new Button("I'm HERE");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(ImHereBtn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        ImHereBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("I'm HERE");

                String[] data = database.getNextTrafficLight(Double.parseDouble(longitudeField.getText()),Double.parseDouble(latitudeField.getText()),1);
                if((data[0].equals("None") || !data[0].equals(prevTrafficLight[0])) && !prevTrafficLight[0].equals("None")) {
                    new CommunicationService().ILeave(prevTrafficLight[1],prevTrafficLight[0],carIdField.getText());
                    return;
                }
                if(data[0].equals("None")) {
                    return;
                }
                prevTrafficLight[0] = data[0];
                prevTrafficLight[1] = data[1];

                new CommunicationService().ImHere(data[1], data[0], carIdField.getText());
            }
        });

        Scene scene = new Scene(grid, 350, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import java.io.File;
import java.util.List;

public class CSVFilePicker extends Application{
    private RedirectProcessor processor;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        //Initialization redirect processor
        processor = new RedirectProcessor();

        //Creating main window
        primaryStage.setTitle("Chose CSV file");

        //Creating "chose file" button
        Button selectFileButton = new Button("Chose File");
        selectFileButton.setOnAction(event -> {
            //Creating chose window
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose CSV file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files","*.csv"));

            //Wyświetlanie okna wyboru pliku i odczyt wybranego pliku
            File selectedFile = (File) fileChooser.showOpenMultipleDialog(primaryStage);
            if (selectedFile != null){
                String csvFilePath = selectedFile.getAbsolutePath();
                System.out.println("Wybrany plik: " + csvFilePath);

                RedirectChecker.readLinksFromCSV(csvFilePath);
            }


        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(selectFileButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}

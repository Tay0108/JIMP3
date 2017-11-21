import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Exceptions.*;
import io.indico.Indico;
import io.indico.api.results.BatchIndicoResult;
import io.indico.api.utils.IndicoException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;



public class Photos extends Application {

    private File photosFolder;
    private File[] photosFiles;

    public void loadPhotos(File directory) {
        photosFiles = directory.listFiles();
    }

    public void sort() throws IndicoException, IOException, NoImagesException, CannotCreateDirectoryException, FileNotMovedException {

        Indico indico = new Indico("1026d4b1cea8318b801f45df22bcb2b5");

        if (photosFiles == null) {
            throw new FileNotFoundException();
        }

        List<String> photosPatches = new ArrayList<>();
        Boolean noImages = true;

        for (int i = 0; i < photosFiles.length; i++) {
            if(!photosFiles[i].isDirectory()) {
                noImages = false;
                photosPatches.add(photosFiles[i].getPath());
            }
        }

        if(noImages) {
            throw new NoImagesException();
        }

        BatchIndicoResult multiple = indico.imageRecognition.predict(photosPatches);
        List<Map<String, Double>> results = multiple.getImageRecognition();

        String maxKey;
        Double maxValue;

        File destinationFolder;
        File sourceFile;
        File destinationFile;

        for (int i = 0; i < results.size(); i++) {
            maxKey = "";
            maxValue = 0.0;
            for (Map.Entry<String, Double> iteratorMap : results.get(i).entrySet()) {

                if (iteratorMap.getValue() > maxValue) {
                    maxKey = iteratorMap.getKey();
                    maxValue = iteratorMap.getValue();
                }
            }
            sourceFile = new File(photosPatches.get(i));
            destinationFolder = new File(photosFolder.getPath() + "/" + maxKey + "/");

            if (!destinationFolder.exists()) {
                if(!destinationFolder.mkdir()) {
                    throw new CannotCreateDirectoryException();
                }
            }
            destinationFile = new File(destinationFolder + "/" + sourceFile.getName());
            if (!sourceFile.renameTo(destinationFile)) {
                throw new FileNotMovedException();
            }
        }
    }

    public void displayPhotos() {

    }

    @Override
    public void start(final Stage stage) {
        stage.setTitle("Indico GUI");

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory with photos to sort");
        directoryChooser.setInitialDirectory(new File("./"));

        final Button openButton = new Button("Browse...");

        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {

                        File directory = directoryChooser.showDialog(stage);
                        if (directory != null) {
                            System.out.println(directory);
                            photosFolder = directory;
                            loadPhotos(directory); // adding photos to list
                        }
                    }
                });

        final Button sortButton = new Button("Sort images");

        sortButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            sort();
                        } catch (IndicoException e) {
                            System.out.println("Wrong API key.");
                        } catch(CannotCreateDirectoryException e) {
                            System.out.println("Cannot create directory");
                        } catch(FileNotMovedException e) {
                            System.out.println("File not moved");
                        } catch(NoImagesException e) {
                            System.out.println("There are no images in this directory");
                        } catch(FileNotFoundException e) {
                            System.out.println("File not found");
                        } catch(UnknownHostException e) {
                            System.out.println("Could not connect to the server");
                        } catch(IOException e) {
                            System.out.println("IO Exception");
                        }
                    }
                });

        ObservableList<String> names = FXCollections
                .observableArrayList();
        ObservableList<String> data = FXCollections.observableArrayList();

        ListView<String> listView = new ListView<String>(data);
        listView.setPrefSize(200, 250);
        listView.setEditable(true);

        names.addAll();

        data.add("Double Click to Select Value");

        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(names));


        final GridPane inputGridPane = new GridPane();

        inputGridPane.getChildren().add(listView);

        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(sortButton, 0, 20);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton, sortButton);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        stage.setScene(new Scene(rootGroup));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

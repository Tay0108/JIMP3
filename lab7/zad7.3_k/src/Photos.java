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
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class Photos extends Application {

    private File photosFolder;
    private File[] photosFiles;
    private ObservableList<String> names = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    private PieChart chart;

    private final GridPane inputGridPane = new GridPane();

    private Indico indico;

    public void loadChartData(String photo, Map<String, Double> adjustment) {
        chart.setTitle("Adjustments for:" + photo);
        for (Map.Entry<String, Double> i : adjustment.entrySet()) {
            pieChartData.add(new PieChart.Data(i.getKey(), i.getValue()));
        }
    }

    public void displayChart(String item) {
        System.out.println(item);
        //inputGridPane.getChildren().add();
    }

    public void loadPhotos(File directory) { // loading photos from selected directory
        photosFiles = directory.listFiles();
    }

    public Map<String, Double> getAdjustment(File photo) throws IndicoException, IOException {
        if (photo == null) {
            throw new FileNotFoundException();
        }
        indico = new Indico("f1cd06f9d3b9857bbecb831f7e4f9592");
        IndicoResult single = indico.imageRecognition.predict(photo.getPath());
        return single.getImageRecognition();
    }

    public void sort() throws IndicoException, IOException, NoImagesException, CannotCreateDirectoryException, FileNotMovedException {

        indico = new Indico("f1cd06f9d3b9857bbecb831f7e4f9592");
        //File photosFolder = new File(photosPath);
        //File[] photosFiles = photosFolder.listFiles();

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
        names.removeAll();
        for (int i = 0; i < photosFiles.length; i++) {
            names.add(photosFiles[i].getPath());
        }
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
                            displayPhotos();
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
                            System.out.println("Bad API key.");
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

        ObservableList<String> data = FXCollections.observableArrayList();

        ListView<String> listView = new ListView<String>(data);
        listView.setPrefSize(400, 250);
        listView.setEditable(true);

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
               displayChart(listView.getSelectionModel().getSelectedItem());
            }
        });

        data.add("Select photo to display its chart");
        /*try {
            File photo = new File("/home/Tay/Projects/Labs/JIMP3/lab7/zad7.3_k/foto/bocian.jpg");
            Map<String, Double> adjustment = getAdjustment(photo);
            loadChartData(photo.getName(), adjustment);
        } catch(Exception e) {
            System.out.println("getAdjustment()");
        }*/
        listView.setItems(names);

        chart = new PieChart(pieChartData);

        GridPane.setConstraints(openButton, 0, 10);
        GridPane.setConstraints(sortButton, 0, 20);
        GridPane.setConstraints(chart, 0, 40);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(listView, openButton, sortButton);

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

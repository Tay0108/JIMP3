import io.indico.Indico;
import io.indico.api.results.BatchIndicoResult;
import io.indico.api.utils.IndicoException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Photos extends Application {

    private File photosFolder;
    private File[] photosFiles;

    public void loadPhotos(File directory) {
        photosFiles = directory.listFiles();
    }

    public void sort(String photoPath)
            throws IndicoException, NoImagesInDirectoryException, IOException,
            CannotCreateDirectoryException, FileNotMovedException {

        Indico indico = new Indico("1026d4b1cea8318b801f45df22bcb2b5");

        if (photosFiles == null) {
            throw new FileNotFoundException();
        }

        /**
         * if there exists some directory in the photosFolder, we do not need it
         * that is why we create new LinkedList photosPatches
         * - this consists of only String paths to files which are not directories
         */

        LinkedList<String> photosPatches = new LinkedList<>();

        for (File photoFile : photosFiles) {
            if (!photoFile.isDirectory()) {
                photosPatches.add(photoFile.getPath());
            }
        }

        if (photosPatches.size() == 0) {
            throw new NoImagesInDirectoryException();
        }

        /*
        now we can start sorting images :)
         */

        BatchIndicoResult multiple = indico.imageRecognition.predict(photosPatches);
        List<Map<String, Double>> results = multiple.getImageRecognition();

        String maxKey;
        Double maxValue;

        File destinationFolder, sourceFile;

        /*
        we iterate through the list of map
        every element of the list concerns different image from our directory
         */
        for (int i = 0; i < results.size(); i++) {
            maxKey = "";
            maxValue = 0.0;

            /*
            we look for the best matches for single image
            Map.Entry<String, Double> is a match of our image to stork, dog, cat, cat etc...
             */
            for (Map.Entry<String, Double> iteratorMap : results.get(i).entrySet()) {

                if (iteratorMap.getValue() > maxValue) {
                    maxValue = iteratorMap.getValue();
                    maxKey = iteratorMap.getKey();
                }
            }

            sourceFile = new File(photosPatches.get(i));
            destinationFolder = new File(photosFolder.getPath() + "\\" + maxKey + "\\");

            if (!destinationFolder.exists()) {
                if (!destinationFolder.mkdir()) {
                    throw new CannotCreateDirectoryException();
                }
            }

            File destinationFile = new File(destinationFolder + "\\" + sourceFile.getName() + "\\");
            if (!sourceFile.renameTo(destinationFile)) {
                throw new FileNotMovedException();
            }
        }
    }

/*
    public static void main(String[] args) {

        Photos photos = new Photos();

        try {
            photos.sort("C:\\Users\\ProBook\\Downloads\\foto");
        } catch (FileNotFoundException e) {
            System.out.println("Directory not found.");
        } catch (NoImagesInDirectoryException e) {
            e.printException();
        } catch (CannotCreateDirectoryException e) {
            e.printException();
        } catch (FileNotMovedException e) {
            e.printException();
        } catch (io.indico.api.utils.IndicoException e) {
            System.out.println("Invalid API key.");
        } catch (java.net.UnknownHostException e) {
            System.out.println("Unknown host.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        stage.setTitle("Photos");

        /*
        buttonBrowse - button to choose directory containing photos
         */

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory with photos to sort");
        directoryChooser.setInitialDirectory(new File("C:\\Users\\ProBook\\Downloads"));

        Button buttonBrowse = new Button();
        buttonBrowse.setText("Browse...");

        buttonBrowse.setOnAction(new EventHandler<>() {

            @Override
            public void handle(ActionEvent event) {

                File directory = directoryChooser.showDialog(stage);
                if (directory != null) {
                    photosFolder = directory;
                    loadPhotos(directory);
                }
            }
        });

        /*
        now we create listViewer
         */

        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        list.setItems(items);



        StackPane root = new StackPane();
        root.getChildren().add(buttonBrowse);
        root.getChildren().add(list);
        stage.setScene(new Scene(root, 300, 250));
        stage.show();
    }
}
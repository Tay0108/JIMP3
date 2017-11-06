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


public class Photos {

    public void sort(String photosPath) throws IndicoException, IOException, NoImagesException, CannotCreateDirectoryException, FileNotMovedException {

        Indico indico = new Indico("f1cd06f9d3b9857bbecb831f7e4f9592");
        File photosFolder = new File(photosPath);
        File[] photosFiles = photosFolder.listFiles();

        if (photosFiles == null) {
            throw new FileNotFoundException();
        }

        List<String> photosPatches = new ArrayList<String>();
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

    public static void main(String[] args) {

        Photos photos = new Photos();

        try {
            photos.sort(args[0]); // path as program parameter
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
}

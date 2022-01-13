package br.unicamp.multimodal_ai.cogtom_cst;

import java.io.InputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class fbAbI10Test {

    // Folder names for test csv files
    public static final String folder = "bAbI_10";

    private ClassLoader loader;
    private InputStream descriptionStream;
    private InputStream scenesStream;
    private InputStream questionsStream;
    private InputStream entitiesStream;
    private InputStream intentionsStream;
    private InputStream affordancesStream;
    private InputStream positioningStream;
    private InputStream eyeDirectionsStream;

    public fbAbI10Test() {
        loader = getClass().getClassLoader();
    }

    @Test
    public void cogTomCstTest() throws InterruptedException {

        descriptionStream = loader.getResourceAsStream(folder + "/description.csv");
        scenesStream = loader.getResourceAsStream(folder + "/scenes.csv");
        questionsStream = loader.getResourceAsStream(folder + "/questions.csv");
        entitiesStream = loader.getResourceAsStream(folder + "/entities.csv");
        intentionsStream = loader.getResourceAsStream(folder + "/intentions.csv");
        affordancesStream = loader.getResourceAsStream(folder + "/affordances.csv");
        positioningStream = loader.getResourceAsStream(folder + "/positioning.csv");
        eyeDirectionsStream = loader.getResourceAsStream(folder + "/eye_directions.csv");

        // Main Class test
        ArrayList<InputStream> inputStreams = new ArrayList<>();
        inputStreams.add(descriptionStream);
        inputStreams.add(scenesStream);
        inputStreams.add(questionsStream);
        inputStreams.add(entitiesStream);
        inputStreams.add(intentionsStream);
        inputStreams.add(affordancesStream);
        inputStreams.add(positioningStream);
        inputStreams.add(eyeDirectionsStream);

        CogTomCst cogTom = new CogTomCst();
        cogTom.run(inputStreams);

        // Wait for the test to finish - this could be of course much better.
        Thread.sleep(5000);
    }
}

package br.unicamp.multimodal_ai.cogtom_cst;

import java.io.InputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class fbAbl1Test {

    // Folder names for test csv files
    public static final String folder = "bAbl_01";

    private ClassLoader loader;
    private InputStream entitiesStream;
    private InputStream intentionsStream;
    private InputStream affordancesStream;
    private InputStream positioningStream;
    private InputStream eyeDirectionsStream;

    public fbAbl1Test() {
        loader = getClass().getClassLoader();
    }

    @Test
    public void cogTomCstTest() throws InterruptedException {
        System.out.println("Facebook bAbl test case 1");

        entitiesStream = loader.getResourceAsStream(folder + "/entities.csv");
        intentionsStream = loader.getResourceAsStream(folder + "/intentions.csv");
        affordancesStream = loader.getResourceAsStream(folder + "/affordances.csv");
        positioningStream = loader.getResourceAsStream(folder + "/positioning.csv");
        eyeDirectionsStream = loader.getResourceAsStream(folder + "/eye_directions.csv");

        // Main Class test
        ArrayList<InputStream> inputStreams = new ArrayList<>();
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

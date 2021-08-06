package br.unicamp.multimodal_ai.cogtom_cst;

import java.io.InputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CogTomCstTest {

    // Folder names for test csv files
    public static final String sally_anne = "sally-anne";
    public static final String fbAbl01 = "bAbl_01";
    public static final String fbAbl02 = "bAbl_02";
    public static final String fbAbl03 = "bAbl_03";
    public static final String fbAbl05 = "bAbl_05";
    public static final String fbAbl06 = "bAbl_06";

    private ClassLoader loader;
    private InputStream entitiesStream;
    private InputStream intentionsStream;
    private InputStream affordancesStream;
    private InputStream eyeDirectionsStream;

    public CogTomCstTest() {
        loader = getClass().getClassLoader();
    }

    @Test
    public void testSallyAnne() {
        System.out.println("Sally-Anne test case");

        entitiesStream = loader.getResourceAsStream(sally_anne + "/entities.csv");
        intentionsStream = loader.getResourceAsStream(sally_anne + "/intentions.csv");
        affordancesStream = loader.getResourceAsStream(sally_anne + "/affordances.csv");
        eyeDirectionsStream = loader.getResourceAsStream(sally_anne + "/eye_directions.csv");

        // Main Class test
        ArrayList<InputStream> inputStreams = new ArrayList<>();
        inputStreams.add(entitiesStream);
        inputStreams.add(intentionsStream);
        inputStreams.add(affordancesStream);
        inputStreams.add(eyeDirectionsStream);

        CogTomCst cogTom = new CogTomCst();
        cogTom.run(inputStreams);
    }

    @Test
    public void testfbAbl01() {
        System.out.println("Facebook bAbl1 test case");

        entitiesStream = loader.getResourceAsStream(fbAbl01 + "/entities.csv");
        intentionsStream = loader.getResourceAsStream(fbAbl01 + "/intentions.csv");
        affordancesStream = loader.getResourceAsStream(fbAbl01 + "/affordances.csv");
        eyeDirectionsStream = loader.getResourceAsStream(fbAbl01 + "/eye_directions.csv");

        // Main Class test
        ArrayList<InputStream> inputStreams = new ArrayList<>();
        inputStreams.add(entitiesStream);
        inputStreams.add(intentionsStream);
        inputStreams.add(affordancesStream);
        inputStreams.add(eyeDirectionsStream);

        CogTomCst cogTom = new CogTomCst();
        cogTom.run(inputStreams);
    }

    @Test
    public void testfbAbl02() {
        System.out.println("Facebook bAbl1 test case");

        entitiesStream = loader.getResourceAsStream(fbAbl02 + "/entities.csv");
        intentionsStream = loader.getResourceAsStream(fbAbl02 + "/intentions.csv");
        affordancesStream = loader.getResourceAsStream(fbAbl02 + "/affordances.csv");
        eyeDirectionsStream = loader.getResourceAsStream(fbAbl02 + "/eye_directions.csv");

        // Main Class test
        ArrayList<InputStream> inputStreams = new ArrayList<>();
        inputStreams.add(entitiesStream);
        inputStreams.add(intentionsStream);
        inputStreams.add(affordancesStream);
        inputStreams.add(eyeDirectionsStream);

        CogTomCst cogTom = new CogTomCst();
        cogTom.run(inputStreams);
    }

    @Test
    public void testfbAbl03() {
        System.out.println("Facebook bAbl1 test case");

        entitiesStream = loader.getResourceAsStream(fbAbl03 + "/entities.csv");
        intentionsStream = loader.getResourceAsStream(fbAbl03 + "/intentions.csv");
        affordancesStream = loader.getResourceAsStream(fbAbl03 + "/affordances.csv");
        eyeDirectionsStream = loader.getResourceAsStream(fbAbl03 + "/eye_directions.csv");

        // Main Class test
        ArrayList<InputStream> inputStreams = new ArrayList<>();
        inputStreams.add(entitiesStream);
        inputStreams.add(intentionsStream);
        inputStreams.add(affordancesStream);
        inputStreams.add(eyeDirectionsStream);

        CogTomCst cogTom = new CogTomCst();
        cogTom.run(inputStreams);
    }

    @Test
    public void testfbAbl05() {
        System.out.println("Facebook bAbl1 test case");

        entitiesStream = loader.getResourceAsStream(fbAbl05 + "/entities.csv");
        intentionsStream = loader.getResourceAsStream(fbAbl05 + "/intentions.csv");
        affordancesStream = loader.getResourceAsStream(fbAbl05 + "/affordances.csv");
        eyeDirectionsStream = loader.getResourceAsStream(fbAbl05 + "/eye_directions.csv");

        // Main Class test
        ArrayList<InputStream> inputStreams = new ArrayList<>();
        inputStreams.add(entitiesStream);
        inputStreams.add(intentionsStream);
        inputStreams.add(affordancesStream);
        inputStreams.add(eyeDirectionsStream);

        CogTomCst cogTom = new CogTomCst();
        cogTom.run(inputStreams);
    }

    @Test
    public void testfbAbl06() {
        System.out.println("Facebook bAbl6 test case");

        entitiesStream = loader.getResourceAsStream(fbAbl06 + "/entities.csv");
        intentionsStream = loader.getResourceAsStream(fbAbl06 + "/intentions.csv");
        affordancesStream = loader.getResourceAsStream(fbAbl06 + "/affordances.csv");
        eyeDirectionsStream = loader.getResourceAsStream(fbAbl06 + "/eye_directions.csv");

        // Main Class test
        ArrayList<InputStream> inputStreams = new ArrayList<>();
        inputStreams.add(entitiesStream);
        inputStreams.add(intentionsStream);
        inputStreams.add(affordancesStream);
        inputStreams.add(eyeDirectionsStream);

        CogTomCst cogTom = new CogTomCst();
        cogTom.run(inputStreams);
    }
}

package utils;

import org.openqa.selenium.Proxy;
import org.zaproxy.clientapi.core.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class zapUtils {
    private static ClientApi clientApi;
    public static Proxy proxy;
    private static ApiResponse apiResponse;
//    public static Properties prop;
//    public Properties envProp;
//    private static String ZAP_ADDRESS;
//    private static int ZAP_PORT;
//    private static String ZAP_API_KEY;

    private static final String ZAP_ADDRESS = "localhost";
    private static final int ZAP_PORT = 8083;
    private static final String ZAP_API_KEY = "critgiiksqvdcmb9jou3i2akl3"; //Please add your own api key from ZAP

    static {
        clientApi = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);
        proxy = new Proxy().setSslProxy(ZAP_ADDRESS + ":" + ZAP_PORT).setHttpProxy(ZAP_ADDRESS + ":" + ZAP_PORT);
    }
//    public zapUtils() {
//
//        if (prop == null) {
//            prop = new Properties();
//            envProp = new Properties();
//
//            try {
//                // Creating a File object for directory
//                File directoryPath = new File(System.getProperty("user.dir") + "/src/test/resources/Properties");
//                // List of all files and directories
//                File[] filesList = directoryPath.listFiles();
//                for (File file : filesList) {
//                    FileInputStream propertyFiles = new FileInputStream(
//                            System.getProperty("user.dir") + "/src/test/resources/properties/" + file.getName());
//                    prop.load(propertyFiles);
//                }
//                ZAP_ADDRESS = prop.getProperty("ZAP_ADDRESS");
//                ZAP_PORT = Integer.parseInt(prop.getProperty("ZAP_PORT"));
//                ZAP_API_KEY = prop.getProperty("ZAP_API_KEY");
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }



    public static void waitTillPassiveScanCompleted() {
        try {
            apiResponse = clientApi.pscan.recordsToScan();
            String tempVal = ((ApiResponseElement) apiResponse).getValue();
            while (!tempVal.equals("0")) {
                System.out.println("passive scan is in progress");
                apiResponse = clientApi.pscan.recordsToScan();
                tempVal = ((ApiResponseElement) apiResponse).getValue();
            }
            System.out.println("passive scan is completed");
        } catch (ClientApiException e) {
            e.printStackTrace();
        }
    }


    public static void generateZapReport(String site_to_test) throws ClientApiException, IOException {

        String report = new String(clientApi.core.htmlreport());

        String filePath = "./reports/zap_report.html";

        // Use FileWriter to write the report content to the file
        FileWriter fileWriter = new FileWriter(filePath);

        // Write the report to the file
        fileWriter.write(report);

        // Close the writer
        fileWriter.close();
//        String title = "Demo Title";
//        String template = "traditional-html";
//        String theme = null;
//        String description = "Demo description";
//        String contexts = null;
//        String sites = site_to_test;
//        String sections = null;
//        String includedconfidences = null;
//        String includedrisks = null;
//        String reportfilename = "Demofilename";
//        String reportfilenamepattern = null;
//        String reportdir = System.getProperty("user.dir");
//        String display = null;
//
//        try {
//            clientApi.reports.generate(title, template, theme, description, contexts, sites, sections,
//                    includedconfidences, includedrisks, reportfilename, reportfilenamepattern, reportdir, display);
//        } catch (ClientApiException e) {
//            e.printStackTrace();
//        }
    }
}

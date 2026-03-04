//import com.intuit.karate.Results;
//import mbbank.auto.mobile.MobileDefaultRuntimeHook;
//import mbbank.auto.report.MergeSuiteReports;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.parallel.Execution;
//import org.junit.jupiter.api.parallel.ExecutionMode;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
///**
// * Runner for CI/CD
// * Support run test suite by tags
// * Support Web default hook, auto screenshot web kw
// *
// * @author nhanha1
// */
//@Execution(ExecutionMode.CONCURRENT)
//public class MultiSuiteRunner {
//
//    static MergeSuiteReports mergeSuiteReports = new MergeSuiteReports();
//
//    /**
//     * Scan all *.feature file in classpath:features folder
//     * You need filter feature to be run by tags from command line
//     * Ex: mvn test -Dtest=ByTagAndHookRunner -Dkarate.options="--tags @run,@TestDynamicLocator
//     */
//    @Test
//    void runInFeatureFolder() {
//        MobileDefaultRuntimeHook mobileHook =
//                new MobileDefaultRuntimeHook("src/test/resources/mobile-devices-note12.json")
//                        .withDeviceKey("deviceId")
//                        .withLoopScenario();
//
//        Results results =
//                com.intuit.karate.Runner.path(
//                                "classpath:features/uat/ValidateUI/Validate_Android_UI/register/Screen_Register.feature")
//                        .tags("@MBA-160521")
//                        .hook(mobileHook)
//                        .outputHtmlReport(false).outputCucumberJson(true).outputJunitXml(false)
//                        .backupReportDir(false)
//                        .parallel(mobileHook.getDeviceCount());
//        mergeSuiteReports.addSuite(results.getSuite());
//        assertTrue(results.getFailCount() == 0, results.getErrorMessages());
//    }
////
////    @Test
////    void runInFeatureFolder2() {
////        MobileDefaultRuntimeHook mobileHook = new MobileDefaultRuntimeHook("src/test/resources/mobile-devices-1.json")
////                .withDeviceKey("deviceId")
////                .withLoopScenario();
////
////        Results results = com.intuit.karate.Runner.path("classpath:features/uat/MainFlow/TranferFlow_Android.feature").tags("@MBA-122943")
////                .hook(mobileHook)
////                .outputHtmlReport(false).outputCucumberJson(false).outputJunitXml(false)
////                .backupReportDir(false)
////                .parallel(mobileHook.getDeviceCount());
////        mergeSuiteReports.addSuite(results.getSuite());
////        assertTrue(results.getFailCount() == 0, results.getErrorMessages());
////    }
//
//    @AfterAll
//    public static void mergeResults() {
//        mergeSuiteReports.generateReport();
//        File reportDir = new File("target/karate-reports");
//        File cucumberReport = new File("target/serenity-reports/cucumber-report.json");
//        File[] jsonFiles = reportDir.listFiles((dir, name) -> name.endsWith(".json"));
//        if (jsonFiles != null && jsonFiles.length > 0)
//        {
//            try
//            {
//                Files.copy(jsonFiles[0].toPath(), cucumberReport.toPath(), StandardCopyOption.REPLACE_EXISTING);
//                System.out.println("Cucumber report copied to: " + cucumberReport.getAbsolutePath());
//                for (File jsonFile : jsonFiles)
//                {
//                    if (!jsonFile.delete())
//                    {
//                        System.err.println("Failed to delete file: " + jsonFile.getAbsolutePath());
//
//                    }
//
//                }
//            }
//            catch (IOException e)
//            {
//                e.printStackTrace();
//                throw new RuntimeException("Failed to copy or delete cucumber report files");
//
//            }
//
//        }
//        else
//        {
//            System.out.println("No cucumber JSON reports found.");
//
//        }
//        String fullCommand = "mvn mb-xray:pushCucumberReport -Dexecution=@MBA-151022";
//        ProcessBuilder builder = new ProcessBuilder();
//        builder.command("cmd.exe", "/c", fullCommand);
//        try
//        {
//            Process process = builder.start();
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())))
//            {
//                String line;
//                while ((line = reader.readLine()) != null)
//                {
//                    System.out.println(line);
//
//                }
//
//            }
//            int exitCode = process.waitFor();
//            if (exitCode != 0)
//            {
//                throw new RuntimeException("Lệnh mvn thất bại với mã lỗi: " + exitCode);
//
//            }
//
//        }
//        catch (IOException | InterruptedException e)
//        {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi khi chạy lệnh Maven pushCucumberReport");
//
//        }
//    }
//
//}
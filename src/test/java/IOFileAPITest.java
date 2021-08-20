import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

public class IOFileAPITest {
    private static String HOME = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGround";

    // Check Files Exist
    @Test
    public void checkFileExist() throws IOException{
        Path homePath = Paths.get(HOME);

        Assertions.assertTrue(Files.exists(homePath));
    }

    // Delete File and Check File Not Exist
    @Test
    public void checkExistingFileDeletedSuccessfully() throws IOException{
        Path playPath = Paths.get(HOME+"/"+PLAY_WITH_NIO);
        if (Files.exists(playPath)) FileUtils.deleteFiles(playPath.toFile());

        Assertions.assertTrue(Files.notExists(playPath));
    }

    // Create Directory
    @Test
    public void createDirectory() throws IOException{
        Path playPath = Paths.get(HOME+"/"+PLAY_WITH_NIO);
        Files.createDirectory(playPath);

        Assertions.assertTrue(Files.exists(playPath));
    }

    // Create File
    @Test
    public void createFile() throws IOException{
        Path playPath = Paths.get(HOME+"/"+PLAY_WITH_NIO);
        if(Files.exists(playPath)){}else {Files.createDirectory(playPath);}
        IntStream.range(1,10).forEach(cntr -> {
            Path tempFile = Paths.get(playPath+"/temp"+cntr);
            Assertions.assertTrue(Files.notExists(tempFile));
            try{ Files.createFile(tempFile);}catch (IOException e) {};
            Assertions.assertTrue(Files.exists(tempFile));
        });

        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path ->  path.toFile().isFile() &&
                                                    path.toString().startsWith("temp"))
                .forEach(System.out::println);
    }

    // List Files
    @Test
    public void listFilesDirectoriesAsWellAsFilesWithExtension() throws IOException{
        Path playPath = Paths.get(HOME+"/"+PLAY_WITH_NIO);
        if(Files.exists(playPath)){}else {Files.createDirectory(playPath);}
        IntStream.range(1,10).forEach(cntr -> {
            Path tempFile = Paths.get(playPath+"/temp"+cntr);
            //Assertions.assertTrue(Files.notExists(tempFile));
            try{ Files.createFile(tempFile);}catch (IOException e) {};
        });

        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        System.out.println("*****");
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        System.out.println("*****");
        Files.newDirectoryStream(playPath, path ->  path.toFile().isFile() &&
                path.toString().startsWith("temp"))
                .forEach(System.out::println);
    }

    @Test
    public void checkBDEmployeeMatchCount(){
        EmployeePayrollService empservice = new EmployeePayrollService();
        List<EmployeePayroll> employeePayrolls = empservice.readEmployeePayrollFromDB();
        Assertions.assertEquals(3, employeePayrolls.size());
    }


}

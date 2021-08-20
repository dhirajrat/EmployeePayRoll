import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class EmployeePayrollServiceIOService {

    private static String HOME = System.getProperty("user.home");
    private static String PAYROLL_FOLDER_NAME = "employee_payroll";
    private static String PAYROLL_FILE_NAME = "payroll_file.txt";
    Path path = Paths.get(HOME+"/"+PAYROLL_FOLDER_NAME+"/"+PAYROLL_FILE_NAME);

    public void writeData(List<EmployeePayroll> employeePayrollList) {

        if(Files.exists(path)){ path.toFile().delete();}
        else {
            try {
                Files.createDirectory(Paths.get(HOME + "/" + PAYROLL_FOLDER_NAME));
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        StringBuffer empBuffer = new StringBuffer();
        employeePayrollList.forEach(employee ->{
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });

        try {
            Files.write(path,empBuffer.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            byte[] fileOutput = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

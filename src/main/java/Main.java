import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<EmployeePayroll> employeePayrollDataList = new ArrayList<>();
        
        EmployeePayrollService employeePayrollImpl = new EmployeePayrollService(employeePayrollDataList);
        Scanner consoleInputReader = new Scanner(System.in);

        employeePayrollImpl.readEmployeePayrollFromConsole(consoleInputReader);
        employeePayrollImpl.readEmployeePayrollFromConsole(consoleInputReader);
        employeePayrollImpl.readEmployeePayrollFromConsole(consoleInputReader);

        employeePayrollImpl.writeEmployeePayroll(IOService.CONSOLE_IO);
        employeePayrollImpl.writeEmployeePayroll(IOService.FILE_IO);
    }
}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

    private List<EmployeePayroll> employeePayrollList;

    /**
     * constructor
     * @param employeePayrollList
     */
    public EmployeePayrollService(List<EmployeePayroll> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    public EmployeePayrollService() {
    }

    /**
     * Read Employee Payroll
     * @param inputRead
     */
    void readEmployeePayrollFromConsole(Scanner inputRead) {
        System.out.print("Enter EmployeeId : ");
        int id = inputRead.nextInt();
        System.out.print("Enter Employee Name : ");
        String name = inputRead.next();
        System.out.print("Enter Employee Salary : ");
        double salary = inputRead.nextDouble();
        employeePayrollList.add(new EmployeePayroll(id, name, salary));
    }

    /**
     * write Employee Payroll
     */
    void writeEmployeePayroll(IOService ioService) {
        if(ioService.equals(IOService.CONSOLE_IO)) {
            employeePayrollList.forEach(employeePayroll -> {
                System.out.println(employeePayroll.toString());
            });
        }
        if(ioService.equals(IOService.FILE_IO)){

            new EmployeePayrollServiceIOService().writeData(employeePayrollList);
        }

    }

    /**
     * Read Employee Payroll DB
     * @return
     */
    public List<EmployeePayroll>  readEmployeePayrollFromDB(){
            List<EmployeePayroll> listOfEmp = new ArrayList<>();
            String sqlQuery = "SELECT * from employeepayroll";
            String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
            String username = "root";
            String password = "793789msw";


            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("driver added");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

                try {
                    Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                    System.out.println("connection Established");
                    Statement statement = connection.createStatement();
                    ResultSet results = statement.executeQuery(sqlQuery);

                    while (results.next()) {
                        int id = results.getInt("id");
                        String name = results.getString("name");
                        double salary = results.getDouble("salary");
                        listOfEmp.add(new EmployeePayroll(id, name, salary));
                    }

                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            return listOfEmp;
    }

}

package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public final class Business extends ServiceProvider{
    private final String NEQ;

    public Business(User user, String accountNum, String transitNum, String bankNum, float total, String neq){
        super(user.email, user.password, user.name, user.phone, user.address, accountNum, transitNum, bankNum, total);
        this.NEQ = neq;
    }

    public Business(User user, String accountNum, String transitNum, String bankNum, Scanner scanner){
        super(user.email, user.password, user.name, user.phone, user.address, accountNum, transitNum, bankNum, 0.0F);
        scanner.nextLine();
        System.out.print("Please enter your NEQ -> ");
        this.NEQ = scanner.nextLine();
    }

    @Override
    public void storeNewServiceProvider(Connection connection){
        super.storeNewServiceProvider(connection);
        String sqlStatement = "INSERT INTO Businesses (userID,NEQ) " +
                "VALUES (?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, this.getUserID(connection));
            preparedStatement.setString(2, this.NEQ);
            preparedStatement.execute();
        }
        catch (SQLException se){
            System.out.println("An error occured trying to insert a new business into the database...");
        }
    }

    @Override
    public String toString(){
        String str = super.toString();
        return str + "NEQ: " + this.NEQ + "\n";
    }
}

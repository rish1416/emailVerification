
package allpckage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDAO {
    
    private static PreparedStatement ps,ps1;
    
    static 
    {
        try
        {
            ps = DBConnection.getConnection().prepareStatement("select * from Emp where username = ?  ;");
            ps1 = DBConnection.getConnection().prepareStatement("insert into Emp values (?,?,?) ;");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean validateUser(String email) throws SQLException
    {
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    
    public static int registerUser(DetailsDTO details) throws SQLException
    {
        int status = 0;
        ps1.setString(1,details.getEmail());
        ps1.setString(2,details.getMobile());
        ps1.setString(3, details.getPassword());
        status = ps1.executeUpdate();
        return status;
    }
    
}


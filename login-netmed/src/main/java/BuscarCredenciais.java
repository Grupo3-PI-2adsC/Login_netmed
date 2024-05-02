import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Driver;
public class BuscarCredenciais {


    String url = "jdbc:mysql://localhost:3306/java";
    String username = "root";
    String password = "MaranatA@2004";


    public Boolean validarCredenciais(String email, String senha){

        Boolean retornoValidar = false;

        Connection con = null;

        String sql = "select * from javaTable where email = '%s' and senha = '%s';".formatted(email,senha);


        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            rs.next();

            String id = rs.getString(1);

            System.out.println("O id do usuario é " + id);

            con.close();

            retornoValidar = true;

        } catch (SQLException e) {
            System.out.println(e);

        }
        return retornoValidar;
    }
}
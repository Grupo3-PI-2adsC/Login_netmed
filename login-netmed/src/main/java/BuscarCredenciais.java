import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuscarCredenciais {


    String url = "jdbc:mysql://localhost:3306/_coloque_nome_database_";
    String username = "_coloque_user_mysql_";
    String password = "_coloque_senha_mysql_";


    public Boolean validarCredenciais(String email, String senha){

        Boolean retornoValidar = false;

        Connection con = null;

        String sql = "select * from testeJava where email = '%s' and senha = '%s';".formatted(email,senha);


        try {
            con = DriverManager.getConnection(url, username, password);

            Statement st = con.createStatement();

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

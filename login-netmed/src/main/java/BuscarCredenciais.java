import org.h2.engine.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Driver;
public class BuscarCredenciais {

<<<<<<< HEAD

    String url = "jdbc:mysql://localhost:3306/_coloque_nome_database_";
    String username = "_coloque_user_mysql_";
    String password = "_coloque_senha_mysql_";

=======
>>>>>>> branch-api-bd
    public Boolean validarCredenciais(String email, String senha){
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        RowMapper<UserTeste> userTesteRowMapper = (rs, rowNum) -> new UserTeste(rs.getInt("id"),
                rs.getString("email"), rs.getString("senha"));
        String sql = "select * from javaTable where email = '%s' and senha = '%s';".formatted(email,senha);

        UserTeste userBanco = con.queryForObject(sql,
                userTesteRowMapper);


        System.out.println(sql);
        System.out.println(userBanco);

        Boolean retornoValidar = true;

        return retornoValidar;
    }


    public void criacaoBanco() {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.execute("DROP TABLE IF EXISTS javaTable;");

        con.execute("""
                    create table javaTable(
                        id int primary key auto_increment, 
                        email varchar(100),
                        senha varchar(50)
                    );""");
        con.execute(
                """
                    Insert into javaTable Values
                            (null, 'matteus@gmail.com', '123456'),
                            (null, 'ket@gmail.com', '12345'),
                            (null, 'gabriel@gmail.com', '1234');
                    """
        );
    }
}
package org.example;

import com.github.britooo.looca.api.core.Looca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Scanner inputText = new Scanner(System.in);

        System.out.println("""
                    ----------------------------------------
                    |                                      |
                    |           Digite o seu email:        |
                    |                                      |
                    ----------------------------------------""");

        String emailLogin = inputText.nextLine();


        System.out.println("""
                    ----------------------------------------
                    |                                      |
                    |           Digite a sua senha:        |
                    |                                      |
                    ----------------------------------------""");
        String senhaLogin = inputText.nextLine();

        System.out.println("""
                    ----------------------------------------
                    |                                      |
                    |        Confirmar a sua senha:        |
                    |                                      |
                    ----------------------------------------""");
        String confirmarSenhaLogin = inputText.nextLine();

        Conexao con = new Conexao();
//        Computador computador = new Computador();
        try {
            Usuario user = con.buscarCredenciais(emailLogin, senhaLogin);
            System.out.println("funfou");
            System.out.println(user);
            con.computadorExiste(1);
        }catch (Exception erro){
            System.out.println(erro);
        }
    }
}

//'1', 'recepção', 'Raimunda', 'raimunda@netmet.com', '1234', '1'

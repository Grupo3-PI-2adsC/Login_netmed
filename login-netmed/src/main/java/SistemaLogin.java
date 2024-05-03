import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import oshi.SystemInfo;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SistemaLogin {

    public static void main(String[] args) {
        BuscarCredenciais cred = new BuscarCredenciais();

        cred.criacaoBanco();

        Scanner input = new Scanner(System.in);
        Scanner inputText = new Scanner(System.in);

        ValidacaoLogin validar = new ValidacaoLogin();
        Boolean validou;

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

            validou = validar.validarLogin(emailLogin, senhaLogin);
            if (validou) {
                Computador computador01 = new Computador();

                computador01.buscarInfos();
            }
    }




}

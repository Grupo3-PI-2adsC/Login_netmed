import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import oshi.SystemInfo;

public class SistemaLogin {

    public static void main(String[] args) {

        SystemInfo si = new SystemInfo();
//        Scanner input = new Scanner(System.in);
//        Scanner inputText = new Scanner(System.in);
//
//        ValidacaoLogin validar = new ValidacaoLogin(si);
          Computador computador = new Computador();
//        Boolean validou;
//
//        do {
//
//
//            System.out.println("""
//                    ----------------------------------------
//                    |                                      |
//                    |           Digite o seu email:        |
//                    |                                      |
//                    ----------------------------------------""");
//
//            String emailLogin = inputText.nextLine();
//
//
//            System.out.println("""
//                    ----------------------------------------
//                    |                                      |
//                    |           Digite a sua senha:        |
//                    |                                      |
//                    ----------------------------------------""");
//            String senhaLogin = inputText.nextLine();
//
//            validou = validar.validarLogin(emailLogin, senhaLogin);
//        }while (!validou.equals(true));
//
//        System.out.println("Bem vindo!!!");

        Looca looca = new Looca();
        Sistema sistema = new Sistema();


        System.out.println(computador);

//        System.out.println(sistema);

//        System.out.println(looca.getMemoria());

//        System.out.println(looca.getRede());

//        System.out.println(looca.getProcessador());

//        System.out.println(looca.getSistema());

//        System.out.println(looca.getDispositivosUsbGrupo());

//        System.out.println(looca.getGrupoDeDiscos());

//        System.out.println(looca.getGrupoDeJanelas());

//        System.out.println(looca.getGrupoDeProcessos());

//        System.out.println(looca.getTemperatura());

    }



}

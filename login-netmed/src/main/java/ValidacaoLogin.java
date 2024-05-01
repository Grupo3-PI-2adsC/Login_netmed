import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.rede.RedeInterfaceGroup;
import com.google.common.graph.Network;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

public class ValidacaoLogin {

    public Boolean validarLogin(String email, String senha) {

        BuscarCredenciais buscar = new BuscarCredenciais();

        if (email.contains(" ") && senha.contains(" ")) {
            System.out.println("""
                    Não pode conter espaços no seu e-mail e senha!!!
                                 --------------------
                           Insita um e-mail e senha válidos""");
            return false;
        }
//        else if   (!senha.contains("!") ||
//                    !senha.contains("#") ||
//                    !senha.contains("$") ||
//                    !senha.contains("¨") ||
//                    !senha.contains("&") ||
//                    !senha.contains("*") ||
//                    !senha.contains("(") ||
//                    !senha.contains(")"))
//            {
//            System.out.println("""
//                            Sua senha tem que conter caracter especial!!!
//                                 --------------------
//                           Insita um e-mail e senha válidos""");
//            return false;
//        }
        Boolean validar;

        validar = buscar.validarCredenciais(email, senha);

        if (validar) {
            return true;
        } else {
            System.out.println("Seu e-mail ou sua senha estão incorretos");
            return false;
        }
    }

}

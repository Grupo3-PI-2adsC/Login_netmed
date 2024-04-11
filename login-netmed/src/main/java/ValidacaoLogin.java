public class ValidacaoLogin {

    public Boolean validarLogin(String email, String senha) {

        BuscarCredenciais buscar = new BuscarCredenciais();

        Boolean validar;

        if(email.contains(" ") && senha.contains(" ")){
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
//                            Seu email não pode ter caracter especial!!!
//                                 --------------------
//                           Insita um e-mail e senha válidos""");
//            return false;
//        }

        validar = buscar.validarCredenciais(email, senha);

        if (validar){
            return true;
        }else {
            System.out.println("Seu e-mail ou sua senha estão incorretos");
            return false;
        }
    }

    public Boolean validarCad(String nomeUserCad, String emailUserCad, String senhaUserCad, String confUserSenhaCad){

        if (!senhaUserCad.equals(confUserSenhaCad)){

            System.out.println("As senhas não são iguais");

            return false;
        }

        Boolean validacao1 = validou(nomeUserCad);
        Boolean validacao2 = validou(emailUserCad);
        Boolean validacao3 = validou(senhaUserCad);
        Boolean validacao4 = validou(confUserSenhaCad);

        if (validacao1 && validacao2 && validacao3 && validacao4){
            return true;
        }

        return false;
    }

    public Boolean validou(String varValidar){





        return true;
    }
}

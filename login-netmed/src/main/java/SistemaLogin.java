import java.util.Scanner;

public class SistemaLogin {
    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        Scanner inputText = new Scanner(System.in);

        ValidacaoLogin validar = new ValidacaoLogin();

        Boolean validou;

        do {


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
        }while (!validou.equals(true));

        System.out.println("Bem vindo!!!");

    }





    //
//        Scanner input = new Scanner(System.in);
//
//        System.out.println("""
//                digite o numero de uma opção:
//
//                1) Login
//                2) Cadastro""");
//
//        Integer opcaoUser = input.nextInt();
//
//        switch (opcaoUser) {
//            case 1 -> Login();
//            case 2 -> Cadastro();
//        }





//    static void Cadastro(){
//
//        Scanner input = new Scanner(System.in);
//        Scanner inputText = new Scanner(System.in);
//
//        ValidacaoLogin validar = new ValidacaoLogin();
//
//        Boolean validou;
//
//        do {
//
//            System.out.println("""
//                    ----------------------------------------
//                    |                                      |
//                    |           Digite o seu nome:         |
//                    |                                      |
//                    ----------------------------------------""");
//
//            String nomeUserCad = inputText.nextLine();
//
//            System.out.println("""
//                    ----------------------------------------
//                    |                                      |
//                    |           Digite o seu email:        |
//                    |                                      |
//                    ----------------------------------------""");
//
//            String emailUserCad = inputText.nextLine();
//
//
//            System.out.println("""
//                    ----------------------------------------
//                    |                                      |
//                    |           Digite a sua senha:        |
//                    |                                      |
//                    ----------------------------------------""");
//            String senhaUserCad = inputText.nextLine();
//
//            System.out.println("""
//                    ----------------------------------------
//                    |                                      |
//                    |      Confirme a sua a sua senha:     |
//                    |                                      |
//                    ----------------------------------------""");
//            String confUserSenhaCad = inputText.nextLine();
//
//            validou = validar.validarCad(nomeUserCad, emailUserCad, senhaUserCad, confUserSenhaCad);
//        }while (!validou.equals(true));
//
//        System.out.println("Cadastro realizado com sucesso");
//
//    }
}

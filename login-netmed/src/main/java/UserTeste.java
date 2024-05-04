public class UserTeste {
    private Integer id;
    private String email;
    private String senha;

    public UserTeste(Integer id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

//    public UserTeste() {
//    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "UserTeste{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}

package gateway.dto;

public class UserInfoDto {
    private Long id;
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

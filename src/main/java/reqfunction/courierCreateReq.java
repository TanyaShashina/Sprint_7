package reqfunction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class courierCreateReq {
    private String login;
    private String password;
    private String firstName;

}
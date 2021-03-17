package lesson6.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lesson6.api.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@SqlResultSetMapping(
        name = "userMapping",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "id", type=Long.class),
                        @ColumnResult(name = "name", type=String.class)
                }
        )
)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @ApiModelProperty("user id")
    private Long id;

    @NotNull
    @Column(name = "name", length = 30)
    @ApiModelProperty("user name")
    private String name;

    @NotNull
    @Column(name = "password_hash", length = 30)
    @ApiModelProperty("hash of password")
    @JsonIgnore
    private String password_hash;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;
}

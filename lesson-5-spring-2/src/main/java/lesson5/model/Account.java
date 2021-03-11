package lesson5.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("account id")
    private Long id;
    @NotNull
    @Column(name = "number", unique = true)
    @ApiModelProperty("unique account number")
    private String number;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}

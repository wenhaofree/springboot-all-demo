package club.fuwenhao.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ext.tianlixia
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;

    public User(String username) {
        this.username = username;
    }
}

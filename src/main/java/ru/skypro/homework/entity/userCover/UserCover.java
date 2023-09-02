package ru.skypro.homework.entity.userCover;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.entity.users.User;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class UserCover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String filePath;
    private String fileSize;
    private String mediaType;
    @Lob
    private byte[] preview;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserCover() {
    }
}

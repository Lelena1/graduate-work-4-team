package ru.skypro.homework.repository.userCover;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.userCover.UserCover;

public interface UserCoverRepository extends JpaRepository<UserCover, Integer> {

    UserCover findByUserId(Integer user);
}

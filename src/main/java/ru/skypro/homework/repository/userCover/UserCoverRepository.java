package ru.skypro.homework.repository.userCover;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.userCover.UserCover;

import java.util.Optional;

public interface UserCoverRepository extends JpaRepository<UserCover, Integer> {


    UserCover findByUserId(Integer id);
}

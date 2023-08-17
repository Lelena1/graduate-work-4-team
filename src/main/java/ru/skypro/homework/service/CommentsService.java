package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.util.List;

public interface CommentsService {
    List<Comments> getComment(int adId);

    Comment addComeent(int adId, Comment comment);

    void deleteComment(int adId, int commentId);

    CreateOrUpdateComment createOrUpdateComment(int adId, int commentId);






}

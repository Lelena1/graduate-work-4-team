package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

import java.util.List;

public interface CommentsService {
    List<CommentsDto> getComment(int adId);

    CommentDto addComeent(int adId, CommentDto comment);

    void deleteComment(int adId, int commentId);

    CreateOrUpdateCommentDto createOrUpdateComment(int adId, int commentId);






}

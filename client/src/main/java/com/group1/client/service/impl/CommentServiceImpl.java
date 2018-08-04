package com.group1.client.service.impl;


import com.group1.client.dao.CommentRepository;
import com.group1.client.service.CommentService;
import com.group1.core.entity.comment.Comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentRepository commentRepository;


    @Override
    public Comment commit(Comment comment) {
        if(comment.getScore()!=null){

        }
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> listAllByShopId(String shopId) {
        return commentRepository.getAllByShopId(shopId);
    }
}

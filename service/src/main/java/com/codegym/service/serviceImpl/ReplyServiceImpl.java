package com.codegym.service.serviceImpl;

import com.codegym.dao.model.Comment;
import com.codegym.dao.model.Reply;
import com.codegym.dao.repository.ReplyRepository;
import com.codegym.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public Page<Reply> findAll(Pageable pageable) {
        return replyRepository.findAll(pageable);
    }

    @Override
    public Page<Reply> findRepliesByComment(Comment comment, Pageable pageable) {
        return replyRepository.findRepliesByComment(comment, pageable);
    }

    @Override
    public Reply findById(Long id) {
        return replyRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public void deleteById(Long id) {
        replyRepository.deleteById(id);
    }

}

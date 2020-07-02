package com.codegym.service;


import com.codegym.dao.model.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplyService {
    Page<Reply> findAll(Pageable pageable);

    Page<Reply> findRepliesByComment(Long commentId, Pageable pageable);

    Reply findById(Long id);

    void save(Reply reply);

    void deleteById(Long id);
}

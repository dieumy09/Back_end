package com.codegym.webservice.controller;

import com.codegym.dao.model.Reply;
import com.codegym.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/replies")
public class ReplyController {
    private ReplyService replyService;

    @Autowired
    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }

    //-------------------Get All Replies--------------------------------------------------------

    @GetMapping()
    public ResponseEntity<List<Reply>> findAllReplies(Pageable pageable){
        List<Reply> replies = (List<Reply>) replyService.findAll(pageable);
        if (replies.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    //-------------------Get One Reply by id--------------------------------------------------------

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reply> findReplyById(@PathVariable("id")Long id){
        Reply reply = replyService.findById(id);
        if (reply == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reply,HttpStatus.OK);
    }

    //-------------------Create a Reply--------------------------------------------------------

    @PostMapping()
    public ResponseEntity<Void> createReply(@RequestBody Reply reply, UriComponentsBuilder uriComponentsBuilder){
        replyService.save(reply);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/{id}").buildAndExpand(reply.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //-------------------Update a Reply--------------------------------------------------------
    @PatchMapping("/{id}")
    public ResponseEntity<Reply> updateReply(@PathVariable("id") Long id, @RequestBody Reply reply){
        replyService.findById(id);
        return null;
    }

    //-------------------Delete a Category--------------------------------------------------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Reply> deleteReply(@PathVariable("id") Long id){
        Reply reply = replyService.findById(id);
        if (reply == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        replyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package org.hzz.controller;

import org.hzz.bean.Content;
import org.hzz.dto.ResponseResult;
import org.hzz.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contents")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/")
    public ResponseResult selectAll(){
        List<Content> contents = contentService.selectAll();
        return new ResponseResult(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                contents);
    }

    @GetMapping("/view/{id}")
    public ResponseResult getById(@PathVariable("id") long id){
        Content content = contentService.getById(id);
        return new ResponseResult(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                content);
    }
}

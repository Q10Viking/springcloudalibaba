package org.hzz.service.impl;

import org.hzz.bean.Content;
import org.hzz.mapper.ContentMapper;
import org.hzz.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Content getById(Long id) {
        return contentMapper.getById(id);
    }

    @Override
    public List<Content> selectAll() {
        return contentMapper.selectAll();
    }
}

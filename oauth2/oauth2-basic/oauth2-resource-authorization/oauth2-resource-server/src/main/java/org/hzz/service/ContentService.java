package org.hzz.service;

import org.hzz.bean.Content;

import java.util.List;

public interface ContentService {
    Content getById(Long id);

    List<Content> selectAll();
}

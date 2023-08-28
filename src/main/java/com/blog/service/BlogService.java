package com.blog.service;

import com.blog.domain.Article;
import com.blog.dto.AddArticleRequest;
import com.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 저장
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}

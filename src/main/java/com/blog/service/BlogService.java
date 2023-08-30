package com.blog.service;

import com.blog.domain.Article;
import com.blog.dto.AddArticleRequest;
import com.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 저장
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " 번 째 글을 찾을 수 없습니다."));
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    public void update(Long id) {

    }
}

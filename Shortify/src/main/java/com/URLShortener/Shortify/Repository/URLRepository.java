package com.URLShortener.Shortify.Repository;

import com.URLShortener.Shortify.Model.URLModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface URLRepository extends JpaRepository<URLModel, Long>  {
    URLModel findByOriginalURL(String originalURL);

    boolean existsByOriginalURL(String originalURL);

    boolean existsByShortenedURL(String shortenedURL);

    URLModel findByShortenedURL(String shortenedURL);
}

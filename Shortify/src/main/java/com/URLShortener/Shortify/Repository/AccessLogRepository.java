package com.URLShortener.Shortify.Repository;

import com.URLShortener.Shortify.Model.AccessLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLogModel, Long> {
}

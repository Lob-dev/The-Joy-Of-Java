package com.blog.prometheus.message.subscriber.global.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayEventRepository extends JpaRepository<PayEvent, Long> {
}

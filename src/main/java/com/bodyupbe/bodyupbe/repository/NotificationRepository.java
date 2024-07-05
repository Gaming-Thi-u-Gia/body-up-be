package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
}

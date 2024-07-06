package com.lucaunlimited.meeting.repositorys;

import com.lucaunlimited.meeting.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByHabIdOrderByOrderDateDesc(Long habId);

    List<OrderEntity> findByHabId(Long habId);
}

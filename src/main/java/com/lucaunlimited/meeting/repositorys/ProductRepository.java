package com.lucaunlimited.meeting.repositorys;

import com.lucaunlimited.meeting.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}

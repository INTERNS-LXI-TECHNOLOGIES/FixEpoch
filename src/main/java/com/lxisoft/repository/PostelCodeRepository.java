package com.lxisoft.repository;

import com.lxisoft.domain.PostelCode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PostelCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostelCodeRepository extends JpaRepository<PostelCode, Long> {
}

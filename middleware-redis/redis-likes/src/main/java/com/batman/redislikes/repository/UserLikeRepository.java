package com.batman.redislikes.repository;

import com.batman.redislikes.entity.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLike, Integer> {
    Page<UserLike> findByLikedUserIdAndStatus(String likedUserId, Integer code, Pageable pageable);

    Page<UserLike> findByLikedPostIdAndStatus(String likedPostId, Integer code, Pageable pageable);

    UserLike findByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId);
}

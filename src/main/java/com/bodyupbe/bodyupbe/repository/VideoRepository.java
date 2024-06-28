package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.model.workout_video.VideoCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VideoRepository extends JpaRepository<Video,Integer> {
    @Query(value = "SELECT COUNT(*) FROM Video v")
    int countVideo();
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Set;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    List<Video> findByNameContainingIgnoreCase(String name);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Video v join v.bookmarkUsers bu where bu.id = :userId and v.url = :url")
    boolean findBookmarkByUserIdAndVideoId(int userId, String url);

    @Query("SELECT v FROM Video v WHERE v.id IN (" +
            "SELECT v1.id FROM Video v1 JOIN v1.videoCategories c1 WHERE c1.id IN :categoryIds " +
            "GROUP BY v1.id HAVING COUNT(c1.id) = :categorySize)")
    Page<Video> findVideosByCategoryIds(@Param("categoryIds") Set<Integer> categoryIds, @Param("categorySize") long categorySize, Pageable pageable);
    Video findVideoByUrl(String url);
}
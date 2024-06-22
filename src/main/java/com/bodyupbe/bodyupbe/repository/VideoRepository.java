package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.model.workout_video.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Set;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    List<Video> findByNameContainingIgnoreCase(String name);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Video v join v.bookmarkUsers bu where bu.id = :userId and v.url = :url")
    boolean findBookmarkByUserIdAndVideoId(int userId, String url);

    Video findVideoByUrl(String url);
}
package com.bodyupbe.bodyupbe.controller.community;

import com.bodyupbe.bodyupbe.model.community.Badge;
import com.bodyupbe.bodyupbe.service.community.BadgeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/badges")
@CrossOrigin
public class BadgeController {
    BadgeService badgeService;

    @GetMapping
    public ResponseEntity<List<Badge>> getAllBadge(){
        return ResponseEntity.ok(badgeService.getBadgeAll());
    }

    @GetMapping("/getBadgeById")
    public ResponseEntity<Badge> getBadgeById(@RequestParam int badgeId) {
        return ResponseEntity.ok(badgeService.getBadgeById(badgeId));
    }


}

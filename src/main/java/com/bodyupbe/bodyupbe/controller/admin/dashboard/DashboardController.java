package com.bodyupbe.bodyupbe.controller.admin.dashboard;

import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.MonthlyUserCountResponseDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.ProductStatisticResponseDto;
import com.bodyupbe.bodyupbe.service.admin.DashboardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {
    DashboardService dashboardService;
    @GetMapping("/dashboard")
    public ResponseEntity<ProductStatisticResponseDto> getTotalElement(){
        return ResponseEntity.ok(dashboardService.getTotalElement());
    }
    @GetMapping("/monthly-user-count")
    public ResponseEntity<List<MonthlyUserCountResponseDto>> getMonthlyUserCount() {
        return ResponseEntity.ok(dashboardService.getMonthlyUserCount());
    }
}

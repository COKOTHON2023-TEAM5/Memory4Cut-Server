package cokothon.Memory4CutServer.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cokothon.Memory4CutServer.global.common.response.ApiResponse;
import cokothon.Memory4CutServer.global.common.response.SuccessType;

@RestController
@RequestMapping("/health")
public class HealthCheck {

    @GetMapping
    public ApiResponse<String> healthCheck() {
        return ApiResponse.success(SuccessType.HEALTH_CHECK_SUCCESS, "test success!");
    }
}

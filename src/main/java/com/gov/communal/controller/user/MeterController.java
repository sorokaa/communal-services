package com.gov.communal.controller.user;

import com.gov.communal.model.meter.dto.MeterPaymentResponse;
import com.gov.communal.model.meter.dto.MeterResponse;
import com.gov.communal.model.meter.request.CreateMeterRequest;
import com.gov.communal.security.SecurityUtil;
import com.gov.communal.service.MeterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@Slf4j
@RestController
@RequestMapping("/api/v1/meters")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority(@authorities.CLIENT)")
public class MeterController {

    private final MeterService meterService;

    @PostMapping
    public MeterResponse create(@RequestBody CreateMeterRequest request) {
        UUID userId = SecurityUtil.getUserId();
        log.debug("Request to create meter. Request: {}, User: {}", request, userId);
        return meterService.create(request, userId);
    }

    @PatchMapping("/{meterId}/payed")
    public void payedMeter(@PathVariable Long meterId) {
        log.debug("Request to pay meter. ID: {}", meterId);
        meterService.payedMeter(meterId);
    }

    @GetMapping("/loan")
    public MeterPaymentResponse getLoans() {
        UUID userId = SecurityUtil.getUserId();
        log.debug("Request to get user loans. ID: {}", userId);
        return meterService.getPaymentsLoan(userId);
    }

    @GetMapping("/loan/export")
    public ResponseEntity<byte[]> exportLoan() {
        UUID userId = SecurityUtil.getUserId();
        log.debug("Request to export user loans. User ID: {}", userId);
        byte[] file = meterService.exportLoans(userId);
        String filename = "report.pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlExposeHeaders(List.of(CONTENT_DISPOSITION));
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(file.length);
        headers.setContentDisposition(ContentDisposition.attachment().filename(filename, StandardCharsets.UTF_8).build());
        return ResponseEntity.ok()
                .headers(headers)
                .body(file);
    }
}

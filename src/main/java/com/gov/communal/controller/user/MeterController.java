package com.gov.communal.controller.user;

import com.gov.communal.model.meter.dto.MeterPaymentResponse;
import com.gov.communal.model.meter.dto.MeterResponse;
import com.gov.communal.model.meter.request.CreateMeterRequest;
import com.gov.communal.service.MeterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@Slf4j
@RestController
@RequestMapping("/api/v1/meters")
@RequiredArgsConstructor
public class MeterController {

    private final MeterService meterService;

    @PostMapping
    public MeterResponse create(@RequestBody CreateMeterRequest request) {
        return meterService.create(request);
    }

    @PatchMapping("/{meterId}/payed")
    public void payedMeter(@PathVariable Long meterId) {
        meterService.payedMeter(meterId);
    }

    @GetMapping("/loan")
    public MeterPaymentResponse getLoans(@RequestParam UUID userId) {
        return meterService.getPaymentsLoan(userId);
    }

    @GetMapping("/loan/export")
    public ResponseEntity<byte[]> exportLoan(@RequestParam UUID userId) {
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

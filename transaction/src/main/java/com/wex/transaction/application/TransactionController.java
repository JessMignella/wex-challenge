package com.wex.transaction.application;

import com.wex.transaction.application.dto.request.TransactionRequest;
import com.wex.transaction.application.dto.response.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionApplication transactionApplication;

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable long transactionId) {
        return ResponseEntity.ok(this.transactionApplication.find(transactionId));
    }

    @PostMapping("/save")
    public ResponseEntity<TransactionResponse> saveTransaction(@Valid @RequestBody TransactionRequest request
    ) {
        return ResponseEntity.ok(this.transactionApplication.save(request));
    }

    @GetMapping("/{transactionId}/convert")
    public ResponseEntity<TransactionResponse> convertToCurrency(@PathVariable long transactionId,
                                                                 @RequestParam(required = false) String currency,
                                                                 @RequestParam(required = false) String country) {
        return ResponseEntity.ok(this.transactionApplication.convertToCurrency(transactionId, currency, country));
    }
}

package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.wex.transaction.application.TransactionApplication;
import com.wex.transaction.application.TransactionController;
import com.wex.transaction.application.dto.response.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransactionControllerTest {

    private TransactionController transactionController;
    private TransactionApplication transactionApplication;

    @BeforeEach
    public void setUp() {
        transactionApplication = mock(TransactionApplication.class);
    }

    @Test
    public void testGetTransaction() {
        long transactionId = 123; // Id da transação a ser testada
        TransactionResponse expectedResponse = new TransactionResponse();
        when(transactionApplication.find(transactionId)).thenReturn(expectedResponse);
        ResponseEntity<TransactionResponse> responseEntity = transactionController.getTransaction(transactionId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}

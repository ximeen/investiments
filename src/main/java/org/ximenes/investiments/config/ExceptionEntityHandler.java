package org.ximenes.investiments.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.ximenes.investiments.domain.stock.exception.StockNotFoundException;
import org.ximenes.investiments.domain.user.exception.UserAlreadyExistsException;
import org.ximenes.investiments.domain.user.exception.UserNotFoundException;
import org.ximenes.investiments.dto.account.exception.AccountNotFoundException;
import org.ximenes.investiments.dto.general.ErrorResponseDTO;

@ControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handlerUserAlreadyExists(UserAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    //     SECTION OF NOT FOUND EXCEPTION
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerUserNotFound(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(StockNotFoundException.class)
        public ResponseEntity<ErrorResponseDTO> handlerStockNotFound(StockNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerAccountNotFound(AccountNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(exception.getMessage()));
    }


}

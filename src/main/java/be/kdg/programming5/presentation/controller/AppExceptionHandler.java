package be.kdg.programming5.presentation.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AppExceptionHandler {

//    @ExceptionHandler({DataAccessException.class})
//    public String handleDatabaseException(DataAccessException ex, Model model) {
//        model.addAttribute("error", ex.getMessage());
//        return "error/databaseerror";
//    }
//
//    @ExceptionHandler(Exception.class)
//    public String handleGeneralException(Exception ex, Model model) {
//        model.addAttribute("error", ex.getMessage());
//        return "error/generalerror";
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleNotFound(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoContent(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"error no content\": \"" + e.getMessage() + "\"}");
    }
}

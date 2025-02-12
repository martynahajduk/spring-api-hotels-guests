package be.kdg.programming5.presentation.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({DataAccessException.class})
    public String handleDatabaseException(DataAccessException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/databaseerror";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error/generalerror";
    }
}

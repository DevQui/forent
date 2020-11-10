package com.springboot.forent.config;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({UserNotFoundException.class, NoSuchElementException.class})
	public final ResponseEntity<Object> handleUserNotFoundException(
			NoSuchElementException exception) {
	    //String exceptionResponse = String.format("Dev, Invalid input parameters: %s\n", exception.getMessage());
	    return new ResponseEntity<>("Data not found in the database", HttpStatus.NOT_FOUND);
	}
	
    /** Provides handling for exceptions throughout this service. */
    /*@ExceptionHandler({ UserNotFoundException.class, ContentNotAllowedException.class, NullPointerException.class })
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof UserNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            UserNotFoundException unfe = (UserNotFoundException) ex;

            return handleUserNotFoundException(unfe, headers, status, request);
        } else if (ex instanceof ContentNotAllowedException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ContentNotAllowedException cnae = (ContentNotAllowedException) ex;

            return handleContentNotAllowedException(cnae, headers, status, request);
        } else if(ex instanceof NullPointerException) {
        	HttpStatus status = HttpStatus.PRECONDITION_REQUIRED;
        	NullPointerException cnae = (NullPointerException) ex;
            return NullPointerException(cnae, headers, status, request);
    	}else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }*/

    /** Customize the response for UserNotFoundException. */
    /**protected ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), headers, status, request);
    }*/

    /** Customize the response for ContentNotAllowedException. */
    /*protected ResponseEntity<ApiError> handleContentNotAllowedException(ContentNotAllowedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex.getErrors()
                .stream()
                .map(contentError -> contentError.getObjectName() + " " + contentError.getDefaultMessage())
                .collect(Collectors.toList());

        return handleExceptionInternal(ex, new ApiError(errorMessages), headers, status, request);
    }*/

    /** A single place to customize the response body of all Exception types. */
    /*protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }*/
}

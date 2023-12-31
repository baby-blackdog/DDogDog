package com.babyblackdog.ddogdog.global.exception;

import static com.babyblackdog.ddogdog.global.exception.ErrorCode.INTERNAL_SERVER_ERROR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(HotelException.class)
    public ResponseEntity<ErrorResponse> placeExceptionHandler(HotelException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        logger.info("HotelException: {}", errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<ErrorResponse> reviewExceptionHandler(ReviewException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        logger.info("ReviewException: {}", errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(RoomException.class)
    public ResponseEntity<ErrorResponse> roomExceptionHandler(RoomException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        logger.info("ReviewException: {}", errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> userExceptionHandler(UserException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        logger.info("UserException: {}", errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(RatingException.class)
    public ResponseEntity<ErrorResponse> ratingExceptionHandler(RatingException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        logger.info("RatingException: {}", errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(CouponException.class)
    public ResponseEntity<ErrorResponse> couponExceptionHandler(CouponException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        logger.info("CouponException: {}", errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(WishlistException.class)
    public ResponseEntity<ErrorResponse> wishlistExceptionHandler(WishlistException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        logger.info("CouponException: {}", errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalServerErrorExceptionHandler(Exception exception) {
        ErrorCode errorCode = INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        logger.info("Exception: {}", errorResponse);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

}

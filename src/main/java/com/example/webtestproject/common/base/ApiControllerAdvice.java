package com.example.webtestproject.common.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.webtestproject.common.dto.ApiResponse;
import com.example.webtestproject.common.exception.XperpCustomException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApiControllerAdvice {

	//private final TelegramService telegramService;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponse<String> handleValidationExceptions(MethodArgumentNotValidException ex,
		HttpServletRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors()
			.forEach(c -> errors.put(((FieldError)c).getField(), c.getDefaultMessage()));
		return ApiResponse.error(errors.toString());
	}

	@ExceptionHandler(XperpCustomException.class)
	public ResponseEntity<ApiResponse<String>> handleXperpCustomException(XperpCustomException ex) {
		int errorCode = ex.getErrorCode();
		ApiResponse<String> response = ApiResponse.error(ex.getMessage(), errorCode);

		if (errorCode >= 1000) {
			errorCode = 400;
		}
		return ResponseEntity.status(errorCode).body(response);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ApiResponse<String> runTimeExceptions(RuntimeException ex) {
		return ApiResponse.error(ex.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public ApiResponse<String> runTimeExceptions(RuntimeException ex, HttpServletRequest request) {
		if (!"/".equals(request.getRequestURI())) {
			log.error("runTimeExceptions : runTimeExceptions");
			//메시지 제외
			String msg = String.format("runTimeExceptions ERROR URL %s : %s", request.getRequestURL(), ex.getMessage());
			log.error(msg);
			//telegramService.sendAegisAwsMessage(msg);
		}
		log.error("ex.getMessage() {}", ex.getMessage());
		return ApiResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
	}

	/**
	 * RequestParam 값 누락시
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse<String> missingServletRequestParameterException(Exception e, HttpServletRequest request) {
		return ApiResponse.error(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse<String> errorException(Exception e, HttpServletRequest request) {
		if (!"/".equals(request.getRequestURI())) {
			switch (e.getClass().getSimpleName()) {
				case "NoResourceFoundException":
				case "MissingServletRequestParameterException":
				case "HttpMediaTypeNotAcceptableException":
					log.error(String.format("Exception ERROR URL %s : %s", request.getRequestURL(), e.getMessage()));
					break;
				default:
					//					log.error("{} {}", e.getClass().getSimpleName(), e.getMessage());
					String msg = String.format("Exception ERROR URL %s : %s", request.getRequestURL(), e.getMessage());
					log.error(msg);
					//telegramService.sendAegisAwsMessage(msg);
					break;
			}
		}
		return ApiResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
}
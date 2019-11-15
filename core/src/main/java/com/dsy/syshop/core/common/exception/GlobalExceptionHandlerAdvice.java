package com.dsy.syshop.core.common.exception;

import com.dsy.syshop.core.common.enums.Code;
import com.dsy.syshop.core.web.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

/**
 * @description: 全局异常处理
 * @author: dsy
 * @date: 2019/11/7 16:33
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException e){
        log.debug("BusinessException:", e);
        return ResponseEntity.ok().body(BaseResponse.with(e.getCode().getCode(), e.getMessage()));
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity handleValidationException(MethodArgumentNotValidException e) {
        log.debug("Validation Exception: ", e);
        FieldError fe = (FieldError) e.getBindingResult().getAllErrors().get(0);
        String message = String.format("%s %s", fe.getField(), StringUtils.isNotBlank(fe.getDefaultMessage()) ? fe.getDefaultMessage() : e.getMessage());
        return ResponseEntity.badRequest().body(BaseResponse.with(Code.PARAMETER_ERROR.getCode(), message));
    }

    /**
     * 违反唯一性约束异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleTokenException(ConstraintViolationException e) {
        log.debug("ConstraintViolationException:", e);
        return ResponseEntity.badRequest().body(BaseResponse.with(Code.CONSTRAIN_VIOLATION.getCode(), e.getMessage()));
    }

    /**
     * 方法参数类型不匹配异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String message = String.format("parameter \"%s\" expects type \"%s\", but got value \"%s\"",
                e.getName(), e.getRequiredType(), e.getValue());
        log.debug(message);
        return ResponseEntity.badRequest().body(BaseResponse.with(Code.PARAMETER_ERROR.getCode(), message));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {
        log.debug("AccessDeniedException:", e);
        return ResponseEntity.badRequest().body(BaseResponse.with(Code.PERMISSION_DENIED.getCode(), e.getMessage()));
    }

    /**
     * 权限验证异常
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationException(AuthenticationException e) {
        log.debug("AuthenticationException:", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponse.with(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }

    /**
     * 参数不存在抛出的异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException:", e);
        e.printStackTrace();
        return ResponseEntity.badRequest().body(BaseResponse.with(Code.PARAMETER_ERROR.getCode(), e.getMessage()));
    }

    /**
     * 默认异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleDefaultException(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.ok(BaseResponse.with(Code.UNKNOWN_EXCEPTION.getCode(), Code.UNKNOWN_EXCEPTION.getMessage(), e.getMessage()));
    }
}

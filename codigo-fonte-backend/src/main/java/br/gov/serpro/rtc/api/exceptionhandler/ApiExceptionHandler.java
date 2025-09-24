/*
 * Versão de Homologação/Testes
 */
package br.gov.serpro.rtc.api.exceptionhandler;

import java.time.Instant;

import br.gov.serpro.rtc.domain.service.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(@NonNull Exception ex, @Nullable Object body, @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode, @NonNull WebRequest request) {
        
        // Estratégia simples: apenas erros 500+ são logados como ERROR (erros reais)
        // Erros 400-499 são comportamento esperado (erros de negócio/validação)
        if (statusCode.is5xxServerError()) {
            // Erro real do sistema - log completo para análise
            log.error("Erro interno: ", ex);
        } else {
            // Erro de negócio/validação - log simples sem stacktrace
            log.info("Erro cliente [{}]: {} | URI: {}", 
                     statusCode.value(), 
                     ex.getMessage(), 
                     request.getDescription(false));
        }
        
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
        // Log sucinto para validação - sem stacktrace
        log.info("Validação falhou: {} campos inválidos | URI: {}", 
                 ex.getBindingResult().getErrorCount(),
                 request.getDescription(false));
        
        ProblemDetail problemDetail = createProblem(ex, httpStatus);
        problemDetail.setTitle("Campos inválidos.");
        StringBuilder detailMessage = new StringBuilder("Um ou mais campos são inválidos - ");
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            detailMessage.append(String.format("[%s: %s] ", error.getField(), error.getDefaultMessage()))
        );
        problemDetail.setDetail(detailMessage.toString());
        return handleExceptionInternal(ex, problemDetail, headers, httpStatus, request);
    }

    @ExceptionHandler(ValidacaoException.class)
    ResponseEntity<Object> handleNegocioException(ValidacaoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ProblemDetail problemDetail = createProblem(ex, status);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CaptchaException.class)
    ResponseEntity<Object> handleCaptchaException(CaptchaException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        
        // Captcha - log com nível WARN (pode ser tentativa maliciosa)
        log.warn("Captcha inválido: {} | URI: {}", ex.getMessage(), request.getDescription(false));
        
        ProblemDetail problemDetail = createProblem(ex, status);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
    }
    
    @ExceptionHandler(CampoInvalidoException.class)
    ResponseEntity<Object> handleCampoInvalidoException(CampoInvalidoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemDetail problemDetail = createProblem(ex, status);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
            WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemDetail problemDetail = createProblem(ex, status);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EstruturaInconsistenteException.class)
    ResponseEntity<Object> handleEstruturaInconsistenteException(EstruturaInconsistenteException ex,
            WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        
        // Estrutura inconsistente - pode ser suspeito, usar WARN
        log.warn("Estrutura inválida: {} | URI: {}", ex.getMessage(), request.getDescription(false));
        
        ProblemDetail problemDetail = createProblem(ex, status);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ErroInternoSistemaException.class)
    ResponseEntity<Object> handleErroInternoSistemaException(ErroInternoSistemaException ex, WebRequest request) {
        return handleGenericException(ex, request);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemDetail problemDetail = createProblem(ex, status);
        return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
    }

    private static <E extends Exception> ProblemDetail createProblem(E ex, HttpStatus status) {
        ProblemType type = ProblemType.from(ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        problemDetail.setType(type.getURI());
        problemDetail.setTitle(type.getTitulo());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

}
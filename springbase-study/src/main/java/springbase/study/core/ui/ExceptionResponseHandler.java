package springbase.study.core.ui;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springbase.study.core.ui.dto.GlobalApiResponse;

@Slf4j
@RestControllerAdvice(basePackages = "springbase.study")
public class ExceptionResponseHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<GlobalApiResponse<Void>> illegalExHandler(IllegalArgumentException e) {
    log.debug(e.getMessage(), e);
    return ResponseEntity.badRequest().body(GlobalApiResponse.fail(e.getMessage()));
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<GlobalApiResponse<Void>> validExHandler(BindException e) {
    log.debug(e.getMessage(), e);
    String message = e.getBindingResult().getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .reduce((s1, s2) -> s1 + "\n" + s2).orElse("");
    return ResponseEntity.badRequest().body(
        GlobalApiResponse.fail(message));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GlobalApiResponse<Void>> exHandler(Exception e) {
    log.error(e.getMessage(), e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(GlobalApiResponse.fail(e.getMessage()));
  }
}

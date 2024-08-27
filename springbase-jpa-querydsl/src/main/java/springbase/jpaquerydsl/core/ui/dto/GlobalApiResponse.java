package springbase.jpaquerydsl.core.ui.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "공통 응답")
@AllArgsConstructor
public class GlobalApiResponse<T> {

  @Schema(description = "에러 메시지")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private final String message;

  @Schema(description = "응답 데이터")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private final T data;

  public static <T> GlobalApiResponse<T> success(T data) {
    return new GlobalApiResponse<>(null, data);
  }

  public static <T> GlobalApiResponse<T> fail(String message) {
    return new GlobalApiResponse<>(message, null);
  }
}

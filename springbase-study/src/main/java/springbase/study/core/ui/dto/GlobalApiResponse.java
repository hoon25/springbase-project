package springbase.study.core.ui.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalApiResponse<T> {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private final String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private final T data;

  public static <T> GlobalApiResponse<T> success(T data) {
    return new GlobalApiResponse<>(null, data);
  }

  public static <T> GlobalApiResponse<T> fail(String message) {
    return new GlobalApiResponse<>(message, null);
  }
}

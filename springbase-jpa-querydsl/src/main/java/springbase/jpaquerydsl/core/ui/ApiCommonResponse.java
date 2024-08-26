package springbase.jpaquerydsl.core.ui;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "공통 응답")
@AllArgsConstructor
@NoArgsConstructor
public class ApiCommonResponse<T> {

  @Schema(description = "응답 데이터")
  private T data;
}

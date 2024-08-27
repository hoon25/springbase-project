package springbase.jpaquerydsl.core.ui;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springbase.jpaquerydsl.core.ui.dto.GlobalApiResponse;

@RestControllerAdvice(basePackages = "springbase.jpaquerydsl")
public class SuccessResponseHandler implements ResponseBodyAdvice<Object> {

  @Override
  public boolean supports(MethodParameter returnType,
      Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType,
      MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {

    // 이미 응답이 래핑된 경우 처리하지 않음
    if (body instanceof GlobalApiResponse) {
      return body;
    }

    // 정상적인 응답을 공통 포맷으로 래핑
    return GlobalApiResponse.success(body);
  }
}

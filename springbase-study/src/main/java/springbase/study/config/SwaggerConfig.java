package springbase.study.config;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OperationCustomizer operationCustomizer() {
    return (operation, handlerMethod) -> {
      this.addResponseBodyWrapperSchemaExample(operation);
      return operation;
    };
  }

  private void addResponseBodyWrapperSchemaExample(Operation operation) {
    final Content content = operation.getResponses().get("200").getContent();
    if (content != null) {
      content.forEach((mediaTypeKey, mediaType) -> {
        Schema<?> originalSchema = mediaType.getSchema();
        Schema<?> wrappedSchema = wrapSchema(originalSchema);
        mediaType.setSchema(wrappedSchema);
      });
    }
  }

  private Schema<?> wrapSchema(Schema<?> originalSchema) {
    final Schema<?> wrapperSchema = new Schema<>();
    wrapperSchema.addProperty("data", originalSchema);
    return wrapperSchema;
  }
}

package springbase.jpaquerydsl.education.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ValidationUnitTest {

  static Validator validator;

  @BeforeAll
  static void setUpValidator() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Data
  static class FixtureDto {

    @Max(value = 10)
    private Integer max10;

    @Min(value = 10)
    private Integer min10;

    @Range(min = 10, max = 20)
    private Integer range10to20;

    @NotBlank
    private String notBlank;

    @NotEmpty
    private String notEmpty;

    @NotNull
    private String notNull;

    private List<@Max(10) Integer> max10List;

    private List<@NotEmpty String> notBlankStringList;
  }

  @DisplayName("Max 테스트")
  @ParameterizedTest(name = "value={0}, isValid={1}, message={2}")
  @CsvSource(value = {"9:True:", "10:True:", "11:False: 10 이하여야 합니다"}, delimiter = ':')
  void maxTest(Integer value, boolean isValid, String message) {
    //given
    FixtureDto dto = new FixtureDto();
    dto.setMax10(value);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto, "max10");
    //then
    if (isValid) {
      assertThat(violations).isEmpty();
    }
    if (!isValid) {
      assertThat(message).isEqualTo(violations.iterator().next().getMessage());
    }
  }

  @DisplayName("Min 테스트")
  @ParameterizedTest(name = "value={0}, isValid={1}, message={2}")
  @CsvSource(value = {"9:False:10 이상이어야 합니다", "10:True:", "11:True:"}, delimiter = ':')
  void minTest(int value, boolean isValid, String message) {
    //given
    FixtureDto dto = new FixtureDto();
    dto.setMin10(value);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto, "min10");
    //then
    if (isValid) {
      assertThat(violations).isEmpty();
    }
    if (!isValid) {
      assertThat(message).isEqualTo(violations.iterator().next().getMessage());
    }
  }

  @DisplayName("Range 테스트")
  @ParameterizedTest(name = "value={0}, isValid={1}, message={2}")
  @CsvSource(value = {"9:False:10에서 20 사이여야 합니다", "10:True:", "20:True:",
      "21:False:10에서 20 사이여야 합니다"}, delimiter = ':')
  void rangeTest(int value, boolean isValid, String message) {
    //given
    FixtureDto dto = new FixtureDto();
    dto.setRange10to20(value);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto,
        "range10to20");
    //then
    if (isValid) {
      assertThat(violations).isEmpty();
    }
    if (!isValid) {
      assertThat(message).isEqualTo(violations.iterator().next().getMessage());
    }
  }

  @DisplayName("NotBlank 테스트")
  @ParameterizedTest(name = "value={0}, isValid={1}, message={2}")
  @CsvSource(value = {"someText:True:", "'':False:공백일 수 없습니다",
      "' ':False:공백일 수 없습니다"}, delimiter = ':')
  void notEmpty(String value, boolean isValid, String message) {
    //given
    FixtureDto dto = new FixtureDto();
    dto.setNotBlank(value);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto,
        "notBlank");
    //then
    if (isValid) {
      assertThat(violations).isEmpty();
    }
    if (!isValid) {
      assertThat(message).isEqualTo(violations.iterator().next().getMessage());
    }
  }

  @DisplayName("NotEmpty 테스트")
  @ParameterizedTest(name = "value={0}, isValid={1}, message={2}")
  @CsvSource(value = {"someText:True:", "'':False:비어 있을 수 없습니다", "' ':True:"}, delimiter = ':')
  void notEmptyTest(String value, boolean isValid, String message) {
    //given
    FixtureDto dto = new FixtureDto();
    dto.setNotEmpty(value);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto,
        "notEmpty");
    //then
    if (isValid) {
      assertThat(violations).isEmpty();
    }
    if (!isValid) {
      assertThat(message).isEqualTo(violations.iterator().next().getMessage());
    }
  }

  @DisplayName("NotNull 테스트")
  @ParameterizedTest(name = "value={0}, isValid={1}, message={2}")
  @CsvSource(value = {"someText:True:", "'':True:", "' ':True:",
      ":False:널이어서는 안됩니다"}, delimiter = ':')
  void notNullTest(String value, boolean isValid, String message) {
    //given
    FixtureDto dto = new FixtureDto();
    dto.setNotNull(value);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto,
        "notNull");
    //then
    if (isValid) {
      assertThat(violations).isEmpty();
    }
    if (!isValid) {
      assertThat(message).isEqualTo(violations.iterator().next().getMessage());
    }
  }

  @DisplayName("Nullable Test")
  @Test
  void nullableTest() {
    //given
    FixtureDto dto = new FixtureDto();
    //when then
    assertAll(
        () -> assertThat(validator.validateProperty(dto, "max10")).isEmpty(),
        () -> assertThat(validator.validateProperty(dto, "min10")).isEmpty(),
        () -> assertThat(validator.validateProperty(dto, "range10to20")).isEmpty(),
        () -> assertThat(validator.validateProperty(dto, "notBlank")).isNotEmpty(),
        () -> assertThat(validator.validateProperty(dto, "notEmpty")).isNotEmpty(),
        () -> assertThat(validator.validateProperty(dto, "notNull")).isNotEmpty()
    );
  }

  @Test
  @DisplayName("[null, 9, 10] / List Max10 Test / Valid")
  void maxListValid() {
    //given
    FixtureDto dto = new FixtureDto();
    List<Integer> li = new ArrayList<>(Arrays.asList(null, 9, 10));
    dto.setMax10List(li);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto, "max10List");
    //then
    assertThat(violations).isEmpty();
  }

  @Test
  @DisplayName("[9, 10, 11] / List Max10 Test / NotValid")
  void maxListNotValid() {
    //given
    FixtureDto dto = new FixtureDto();
    List<Integer> li = new ArrayList<>(Arrays.asList(9, 10, 11));
    dto.setMax10List(li);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto, "max10List");
    //then
    assertThat(violations).isNotEmpty();
  }


  @Test
  @DisplayName("['someText', ' '] / List NotEmpty Test / Valid")
  void notEmptyStringListValid() {
    //given
    FixtureDto dto = new FixtureDto();
    List<String> li = new ArrayList<>(Arrays.asList("someText", " "));
    dto.setNotBlankStringList(li);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto,
        "notBlankStringList");
    //then
    assertThat(violations).isEmpty();
  }

  @Test
  @DisplayName("['someText', ''] / List NotEmpty Test / NotValid")
  void notEmptyStringListNotValid() {
    //given
    FixtureDto dto = new FixtureDto();
    List<String> li = new ArrayList<>(Arrays.asList("someText", ""));
    dto.setNotBlankStringList(li);
    //when
    Set<ConstraintViolation<FixtureDto>> violations = validator.validateProperty(dto,
        "notBlankStringList");
    //then
    assertThat(violations).isNotEmpty();
  }
}

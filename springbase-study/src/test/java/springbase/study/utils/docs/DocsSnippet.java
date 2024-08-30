package springbase.study.utils.docs;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import java.util.stream.Stream;
import org.springframework.restdocs.payload.FieldDescriptor;

public class DocsSnippet {

  public static final FieldDescriptor RESPONSE_PREFIX_SNIPPET = fieldWithPath("data").description(
      "기본 응답 본문 PATH");
  public static final FieldDescriptor RESPONSE_PAGING_PREFIX_SNIPPET = fieldWithPath(
      "data.content[]").description(
      "페이징 응답 본문 PATH");

  public static final String RESPONSE_PREFIX_PATH = "data.";
  public static final String RESPONSE_PAGING_PREFIX_PATH = "data.content[].";


  public static final FieldDescriptor[] RESPONSE_PAGING_FIELDS = {
      fieldWithPath("pageable").description("페이지 정보"),
      fieldWithPath("last").description("마지막 페이지 여부"),
      fieldWithPath("totalPages").description("전체 페이지 수"),
      fieldWithPath("totalElements").description("전체 요소 수"),
      fieldWithPath("first").description("첫 페이지 여부"),
      fieldWithPath("size").description("페이지 크기"),
      fieldWithPath("number").description("페이지 번호"),
      fieldWithPath("numberOfElements").description("현재 페이지의 요소 수"),
      fieldWithPath("empty").description("비어있는지 여부"),
      fieldWithPath("sort.empty").description("정렬 여부"),
      fieldWithPath("sort.sorted").description("정렬 여부"),
      fieldWithPath("sort.unsorted").description("정렬 여부")
  };


  public static FieldDescriptor[] withPaginationSnippets(FieldDescriptor... fieldDescriptors) {
    return Stream.concat(Stream.of(fieldDescriptors), Stream.of(RESPONSE_PAGING_FIELDS))
        .toArray(FieldDescriptor[]::new);
  }


}

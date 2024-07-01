package com.springbase.member.ui;

import com.springbase.member.command.application.JoinMemberRequest;
import com.springbase.member.command.application.JoinMemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
@Tag(name = "Member", description = "Member API")
public class MemberController {

  private final JoinMemberService joinMemberService;

  @PostMapping("")
  public ResponseEntity<Long> join(@RequestBody JoinMemberRequest request) {
    Long memberId = this.joinMemberService.join(request);
    return ResponseEntity.created(URI.create("/member/" + memberId)).body(memberId);
  }
}

package com.picmap.app.reply;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.picmap.app.member.MemberDTO;

@Controller
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addReply(@RequestParam Long commentNum, @RequestParam String content, HttpSession session) throws Exception {
        // 세션에서 로그인한 사용자의 정보를 가져옵니다.
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        if (memberDTO == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        Long memberNum = memberDTO.getMemberNum();
        if (memberNum == null) {
            return ResponseEntity.status(400).body("회원 정보가 잘못되었습니다.");
        }

        // ReplyDTO 객체를 생성하고 데이터를 설정합니다.
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setCommentNum(commentNum);
        replyDTO.setMemberNum(memberNum);
        replyDTO.setContent(content);

        int result = replyService.addReply(replyDTO);
        if (result <= 0) {
            throw new Exception("답글 추가 실패");
        }

        return ResponseEntity.ok("success");
    }
	
	@GetMapping("/list")
    public ResponseEntity<List<ReplyDTO>> getReplies(@RequestParam Long commentNum) {
        List<ReplyDTO> replies = replyService.getRepliesByCommentNum(commentNum);
        return ResponseEntity.ok(replies);
    }
}
	


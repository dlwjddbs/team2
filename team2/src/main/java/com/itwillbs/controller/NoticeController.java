package com.itwillbs.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.NoticeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class NoticeController {
	
	private final NoticeService noticeService;
       
    @GetMapping("/notice/noticeList")
    public String getNoticeList(HttpSession session, @RequestParam Map<String, Object> map, Model model) {
    	
    	if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
        
        String id = session.getAttribute("id").toString();
    	
    	
    	map.put("memberId", id);
    	List<Map<String, Object>> noticeList = noticeService.getNoticeList(map);
    	
    	 // todayHistory가 null이면 확인
	    if (noticeList == null) {
	        System.out.println("noticeList is null");
	    } else {
	    }
    	
    	model.addAttribute("noticeList", noticeList);
    	
    	return "/notice/noticeList";
    }
    
    @PostMapping("/getNoticeList")
    @ResponseBody
    public List<Map<String, Object>> getNoticeList(HttpSession session, @RequestParam Map<String, Object> map) {
    	String id = session.getAttribute("id").toString();
        map.put("memberId", id);
        List<Map<String, Object>> noticeList = noticeService.getNoticeList(map);
        System.out.println("noticeList: " + noticeList);
        return noticeList;
    }
    
    
//  공지사항 작성 (관리자 전용)
    @PostMapping("/createNotice")
    @ResponseBody
    public Map<String, Object> createNotice(@RequestParam Map<String, Object> map, Model model) {
        String id = "admin"; // 하드코딩된 관리자 ID
        map.put("memberId", id);
        noticeService.createNotice(map);
        
        Map<String, Object> response = new HashMap<>();
          
        response.put("success", true);
        return response;
    }

// // 공지사항 수정 (관리자 전용)
//    @PostMapping("/updateNotice")
//    public String updateNotice(@RequestParam Map<String, Object> map) {
//        String id = ADMIN_ID; // 하드코딩된 관리자 ID
//        map.put("id", id);
//
//        if (!ADMIN_ID.equals(id)) {
//            return "관리자만 공지사항을 수정할 수 있습니다.";
//        }
//
//        noticeService.updateNotice(map);
//        return "공지사항이 성공적으로 수정되었습니다.";
//    }
//
//    // 공지사항 삭제 (관리자 전용)
//    @PostMapping("/deleteNotice")
//    public String deleteNotice(@RequestParam Map<String, Object> map) {
//        String id = ADMIN_ID; // 하드코딩된 관리자 ID
//        map.put("id", id);
//
//        if (!ADMIN_ID.equals(id)) {
//            return "관리자만 공지사항을 삭제할 수 있습니다.";
//        }
//
//        noticeService.deleteNotice(map);
//        return "공지사항이 성공적으로 삭제되었습니다.";
//    }
//	
	
	
	
}

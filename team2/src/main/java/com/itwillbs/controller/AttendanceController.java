package com.itwillbs.controller;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.AttendanceService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class AttendanceController {
	
	private final AttendanceService attendanceService;
	
	/*====================================================================
	 * - 목차 -
	 * 1. 회원 출퇴근 기록 조회
	 * 2. 회원 출퇴근 기준 시간 조회
	 * 3. 회원 지각/조퇴/결근 내역 조회
	 * 4. 관리자 출퇴근 기록 관리
	 * 5. 관리자 출퇴근 기준 시간 관리
	 * 6. 관리자 지각/조퇴/결근 내역 관리
	 * 7. 메인 근태
	 * 8. 공휴일 달력
	 * 9. ...
	 * ===================================================================
	 * */
	
	
	
	
	/*====================================================================
	 * 1. 회원 출퇴근 기록 조회
	 * ===================================================================
	 * */
	
	@GetMapping("/myCommuteHistory")
	public String getMyCommuteHistory(HttpSession session, Model model, Map<String, Object> map) {
        if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
        
        String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		Map<String, Object> commuteMinMaxDate = attendanceService.getMyCommuteHistoryMinMaxDate(map);
		
		if (commuteMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteMinMaxDate = new HashMap<>();
			commuteMinMaxDate.put("COMMUTE_MIN_DATE", now);
			commuteMinMaxDate.put("COMMUTE_MAX_DATE", now);
		}
		
		model.addAttribute("commuteMinMaxDate", commuteMinMaxDate);
		
		return "/attendance/myCommuteHistory";
	}
	
	@PostMapping("/getMyCommuteHistory")
	@ResponseBody
	public List<Map<String, Object>> getMyCommuteHistory(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		List<Map<String, Object>> commuteHistory = attendanceService.getMyCommuteHistory(map);
		
		return commuteHistory;
	}
	
	/*====================================================================
	 * 2. 회원 출퇴근 기준 시간 조회
	 * ===================================================================
	 * */
	
	@GetMapping("/myAttendanceTime")
	public String getMyAttendanceTime(HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
        
        String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		Map<String, Object> commuteTimeMinMaxDate = attendanceService.getMyCommuteTimeMinMaxDate(map);
		
		if (commuteTimeMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteTimeMinMaxDate = new HashMap<>();
			commuteTimeMinMaxDate.put("MIN_STARTING_DATE", now);
			commuteTimeMinMaxDate.put("MAX_STARTING_DATE", now);
		}
		
		model.addAttribute("commuteTimeMinMaxDate", commuteTimeMinMaxDate);
		
		return "/attendance/myAttendanceTime";
	}
	
	@PostMapping("/getMyAttendanceTime")
	@ResponseBody
	public List<Map<String, Object>> getMyAttendanceTime(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		List<Map<String, Object>> commuteTime = attendanceService.getMyCommuteTime(map);
		
		return commuteTime;
	}
	
	
	/*====================================================================
	 * 3. 회원 지각/조퇴/결근 내역 조회
	 * ===================================================================
	 * */
	
	@GetMapping("/myAttendanceHistory")
	public String getMyAttendanceHistory(HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
        
        String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		Map<String, Object> commuteMinMaxDate = attendanceService.getMyCommuteHistoryMinMaxDate(map);
		
		if (commuteMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteMinMaxDate = new HashMap<>();
			commuteMinMaxDate.put("COMMUTE_MIN_DATE", now);
			commuteMinMaxDate.put("COMMUTE_MAX_DATE", now);
		}
		
		model.addAttribute("commuteMinMaxDate", commuteMinMaxDate);
		
		return "/attendance/myAttendanceHistory";
	}
	
	@PostMapping("/getMyAttendanceHistory")
	@ResponseBody
	public List<Map<String, Object>> getMyAttendanceHistory(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		List<Map<String, Object>> attendanceHistory = attendanceService.getMyAttendanceHistory(map);
		
		return attendanceHistory;
	}
	
	@PostMapping("/getMyAttendanceHistoryDonutChart")
	@ResponseBody
	public List<Map<String, Object>> getMyAttendanceHistoryDonutChart(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		List<Map<String, Object>> attendanceHistoryDonut = attendanceService.getMyAttendanceHistoryDonutChart(map);
		
		return attendanceHistoryDonut;
	}
	
	/*====================================================================
	 * 4. 관리자 출퇴근 기록 관리
	 * ===================================================================
	 * */
	@GetMapping("/commuteHistory")
	public String getCommuteHistory(HttpServletRequest request, HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
        
        String id = session.getAttribute("id").toString();
		map.put("id", id);
		
        // 관리자 아닐 시 뒤로 보내기
//        else if (!authority.equals("AD") && request.getHeader("Referer") != null) {
//        	return "redirect:" + request.getHeader("Referer");
//        }
		
		Map<String, Object> commuteMinMaxDate = attendanceService.getMyCommuteHistoryMinMaxDate(map);
		
		if (commuteMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteMinMaxDate = new HashMap<>();
			commuteMinMaxDate.put("COMMUTE_MIN_DATE", now);
			commuteMinMaxDate.put("COMMUTE_MAX_DATE", now);
		}
		
		model.addAttribute("commuteMinMaxDate", commuteMinMaxDate);
		
		return "/attendance/commuteHistory";
	}
	
	@PostMapping("/getCommuteHistory")
	@ResponseBody
	public List<Map<String, Object>> getCommuteHistory(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> commuteHistory = attendanceService.getMyCommuteHistory(map);
		
		return commuteHistory;
	}
	
	@PostMapping("/deleteCommuteTime")
	@ResponseBody
	public Map<String, Object> deleteCommuteTime(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = attendanceService.deleteCommuteTime(map);
		
		return message;
	}
	
	@PostMapping("/addCommuteTime")
	@ResponseBody
	public Map<String, Object> addCommuteTime(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("session_id", id);
		
		Map<String, Object> message = attendanceService.addCommuteTime(map);
		
		return message;
	}

	
	/*====================================================================
	 * 5. 관리자 출퇴근 기준 시간 관리
	 * ===================================================================
	 * */
	
	@GetMapping("/attendanceTime")
	public String attendanceTime(HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
        
        String id = session.getAttribute("id").toString();
        
		Map<String, Object> commuteTimeMinMaxDate = attendanceService.getMyCommuteTimeMinMaxDate(map);
		
		if (commuteTimeMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteTimeMinMaxDate = new HashMap<>();
			commuteTimeMinMaxDate.put("MIN_STARTING_DATE", now);
			commuteTimeMinMaxDate.put("MAX_STARTING_DATE", now);
		}
		
		model.addAttribute("commuteTimeMinMaxDate", commuteTimeMinMaxDate);
		
		return "/attendance/attendanceTime";
	}
	
	@PostMapping("/getAttendanceTime")
	@ResponseBody
	public List<Map<String, Object>> getAttendanceTime(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> commuteTime = attendanceService.getMyCommuteTime(map);
		
		return commuteTime;
	}
	
	@PostMapping("/deleteAttendanceTime")
	@ResponseBody
	public Map<String, Object> deleteAttendanceTime(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = attendanceService.deleteAttendanceTime(map);
		
		return message;
	}
	
	
	@PostMapping("/addAttendanceTime")
	@ResponseBody
	public Map<String, Object> addAttendanceTime(@RequestParam Map<String, Object> map) {
		Map<String, Object> message = attendanceService.addAttendanceTime(map);
		
		return message;
	}
	
	/*====================================================================
	 * 6. 관리자 지각/조퇴/결근 내역 관리
	 * ===================================================================
	 * */
	
	@GetMapping("/attendanceHistory")
	public String attendanceHistory(HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
        
        String id = session.getAttribute("id").toString();
        
		Map<String, Object> commuteMinMaxDate = attendanceService.getMyCommuteHistoryMinMaxDate(map);
		
		if (commuteMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			commuteMinMaxDate = new HashMap<>();
			commuteMinMaxDate.put("COMMUTE_MIN_DATE", now);
			commuteMinMaxDate.put("COMMUTE_MAX_DATE", now);
		}
		
		model.addAttribute("commuteMinMaxDate", commuteMinMaxDate);
		
		return "/attendance/attendanceHistory";
	}
	
	@PostMapping("/getAttendanceHistory")
	@ResponseBody
	public List<Map<String, Object>> getAttendanceHistory(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> attendanceHistory = attendanceService.getMyAttendanceHistory(map);
		
		return attendanceHistory;
	}
	
	@PostMapping("/getAttendanceHistoryDonutChart")
	@ResponseBody
	public List<Map<String, Object>> getAttendanceHistoryDonutChart(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> attendanceHistoryDonut = attendanceService.getMyAttendanceHistoryDonutChart(map);
		
		return attendanceHistoryDonut;
	}
	
	@PostMapping("/getAttendanceHistoryStackedBarChart")
	@ResponseBody
	public List<Map<String, Object>> getAttendanceHistoryStackedBarChart(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> attendanceHistoryStackedBar = attendanceService.getAttendanceHistoryStackedBarChart(map);
		
		return attendanceHistoryStackedBar;
	}
	
	/*====================================================================
	 * 7. 메인 근태
	 * ===================================================================
	 * */
	
	@GetMapping("/myCommuteHistoryCal")
	public String getMyCommuteHistoryCal(HttpSession session, Model model, Map<String, Object> map) {
		if (session.getAttribute("id") == null) {
            return "redirect:/login"; 
        }
		
		return "/attendance/myCommuteHistoryCal";
	}
	
	@PostMapping("/insertCheckTime")
	@ResponseBody
	public Map<String, Object> insertCheckTime(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		Map<String, Object> message = attendanceService.insertCheckTime(map);
		
		return message;
	}
	
	@PostMapping("/getWeekendTotalWorkHour")
	@ResponseBody
	public double getWeekendTotalWorkHour(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		double count = attendanceService.getWeekendTotalWorkHour(map);
		
		return count;
	}
	
	@PostMapping("/getUserInfo")
	@ResponseBody
	public List<Map<String, Object>> getUserInfo(HttpSession session, @RequestParam Map<String, Object> map) {
		String id = session.getAttribute("id").toString();
		map.put("id", id);
		
		List<Map<String, Object>> userInfo = attendanceService.getUserInfo(map);
		
		return userInfo;
	}
	
	/*====================================================================
	 * 8. 공휴일 달력
	 * ===================================================================
	 * */
	
	@GetMapping("/setHoliday")
	public String setHoliday(Model model, Map<String, Object> map) {
		Map<String, Object> holidayMinMaxDate = attendanceService.getHolidayMinMaxDate(map);
		
		if (holidayMinMaxDate == null) {
			LocalDate now = LocalDate.now();
			
			holidayMinMaxDate = new HashMap<>();
			holidayMinMaxDate.put("HOLIDAY_MIN_DATE", now);
			holidayMinMaxDate.put("HOLIDAY_MAX_DATE", now);
		}
		
		model.addAttribute("holidayMinMaxDate", holidayMinMaxDate);
		
		return "attendance/setHoliday";
	}
	
	@PostMapping("/setHoliday")
	@ResponseBody
	public Map<String, Object> setHoliday(@RequestBody Map<String, Object> map) {
		Map<String, Object> message = attendanceService.setHoliday(map);
		
	    return message;
	}
	
	@PostMapping("/getHoliday")
	@ResponseBody
	public List<Map<String, Object>> getHoliday(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> holidayList = attendanceService.getHoliday(map);
		
		return holidayList;
	}
	
	/*====================================================================
	 * 9. ...
	 * ===================================================================
	 * */

	@PostMapping("/selectBox")
	@ResponseBody
	public List<Map<String, Object>> selectBox(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> selectBoxList = attendanceService.getSelectBoxList(map);
		
		return selectBoxList;
	}
	
	@PostMapping("/memberSelectBox")
	@ResponseBody
	public List<Map<String, Object>> memberSelectBox(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> memberSelectBoxList = attendanceService.getMemberSelectBoxList(map);
		
		return memberSelectBoxList;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model, Map<String, Object> map) {
		session.invalidate();
		return "redirect:/login";
	}
}




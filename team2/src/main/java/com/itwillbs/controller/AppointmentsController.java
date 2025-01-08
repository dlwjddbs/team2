package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.AppointmentsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class AppointmentsController {

	private final AppointmentsService appointmentsService;

	@GetMapping("/personnelAppointments")
	public String getAppointList(@RequestParam Map<String, Object> map, Model model) {
		List<Map<String, Object>> list = appointmentsService.getAppointList(map);
		model.addAttribute("appointList", list);
		return "HRManagement/personnelAppointments";
	}

	@GetMapping("/getMinMaxDate")
	@ResponseBody
	public Map<String, String> getMinMaxDate() {
		return appointmentsService.getMinMaxDate();
	}

	@PostMapping("/getAppointList")
	@ResponseBody
	public List<Map<String, Object>> getAppointList(@RequestParam Map<String, Object> map) {
		return appointmentsService.getAppointList(map);
	}

	@GetMapping("/getAllDepartments")
	public ResponseEntity<List<Map<String, Object>>> getAllDepartments() {
		List<Map<String, Object>> departments = appointmentsService.getAllDepartments();
		return ResponseEntity.ok(departments);
	}

	@GetMapping("/getAllRanks")
	public ResponseEntity<List<Map<String, Object>>> getAllRanks() {
		List<Map<String, Object>> ranks = appointmentsService.getAllRanks();
		return ResponseEntity.ok(ranks);
	}

	@GetMapping("/getOrgTree")
	@ResponseBody
	public List<Map<String, Object>> getOrgTree() {
		return appointmentsService.getOrgTree();
	}

	@GetMapping("/appointmentsNotice")
	public String getAppointList2(@RequestParam Map<String, Object> map, Model model) {
		List<Map<String, Object>> list = appointmentsService.getAppointList(map);
		model.addAttribute("appointList", list);
		return "HRManagement/appointmentsNotice";
	}

	@PostMapping("/updateAppointments")
	@ResponseBody
	public String updateAppointments(@RequestBody List<Map<String, Object>> employeeData) {
		try {
			appointmentsService.updateAppointments(employeeData);
			return "발령 정보가 성공적으로 저장되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			return "발령 정보 저장 중 오류가 발생했습니다.";
		}
	}

	@PostMapping("/updateChangedColumns")
	public ResponseEntity<?> updateChangedColumns(@RequestBody List<Map<String, Object>> updatedData) {
		try {
			if (updatedData == null || updatedData.isEmpty()) {
				return ResponseEntity.badRequest().body("업데이트할 데이터가 없습니다.");
			}

			// Service 호출
			appointmentsService.updateChangedAppointments(updatedData);

			return ResponseEntity.ok("변경 사항이 성공적으로 저장되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("변경 사항 저장 중 오류가 발생했습니다.");
		}
	}
}

package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<List<Map<String, Object>>> getAllRanks(){
    	List<Map<String, Object>> ranks = appointmentsService.getAllRanks();
    	return ResponseEntity.ok(ranks);
    }

}

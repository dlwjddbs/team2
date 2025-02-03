package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.service.PurchaseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequiredArgsConstructor
@Log
public class PurchaseController {
	
	private final PurchaseService purchaseService;

	@GetMapping("/purchase")
	public String purchase(@AuthenticationPrincipal User user) {
//		if (session.getAttribute("id") == null) {
//      return "redirect:/login"; 
//  }
		return "/purchase/purchaseOrder";
	}
	
		@PostMapping("getPurchase")
		@ResponseBody
		public List<Map<String, Object>> getPurchaseList(@RequestBody Map<String, Object> map) {
			List<Map<String, Object>> purchaseList = purchaseService.getPurchaseList(map);
			System.out.println("받은 리스트 :" + map.toString());
			System.out.println("purchaseList: " + purchaseList);
			return purchaseList;
		}
		
		
		@PostMapping("/savePurchases")
		@ResponseBody
		public int savePurchases(@RequestBody List<Map<String, Object>> dataList) {
		    int result = 0;

		    List<Map<String, Object>> insertList = new ArrayList<>();
		    List<Map<String, Object>> updateList = new ArrayList<>();
		    List<Map<String, Object>> deleteList = new ArrayList<>();

		    for (Map<String, Object> data : dataList) {
		        String rowType = (String) data.get("rowType");

		        if ("insert".equals(rowType)) {
		            insertList.add(data);
		        } else if ("update".equals(rowType)) {
		            updateList.add(data);
		        } else if ("delete".equals(rowType)) {
		            deleteList.add(data);
		        }
		    }

		    result += purchaseService.insertPurchases(insertList);
		    result += purchaseService.updatePurchases(updateList);
		    result += purchaseService.deletePurchases(deleteList);

		    return result;
		}
		
		
		
		
//		// 발주 등록
//		@PostMapping("addPurchase")
//		@ResponseBody
//		public int addPurchase(@RequestParam Map<String, Object> map) {
//			System.out.println("받은 데이터: " + map.toString());
//			if(map.get("rowType").equals("insert")) {
//				
//			}
//			//int result = purchaseService.insertPurchase(map);
//			
//			return 1;
//		}
	
	
	
}

//package com.ms.vidhyalebox.dashboard;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/dashboard")
//public class DashboardController {
//
//    private final DashboardService dashboardService;
//
//    public DashboardController(DashboardService dashboardService) {
//        this.dashboardService = dashboardService;
//    }
//
//    @GetMapping("/")
//    public Map<String, Integer> getOrgMemberDetails(@RequestParam("orgUniqId") String orgUniqId) {
//        return dashboardService.getOrgMemberDetails(orgUniqId);
//    }
//}

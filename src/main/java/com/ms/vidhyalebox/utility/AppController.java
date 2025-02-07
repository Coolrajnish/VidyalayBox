package com.ms.vidhyalebox.utility;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class AppController {

//    @Autowired
//    BuildProperties buildProperties;
//
//	    @GetMapping("/health")
//	    public AppHealthDTO health() {
//	    	AppHealthDTO appHealthDTO = AppHealthDTO.builder()
//	    			.appGroup(buildProperties.getGroup())
//	    			.appName(buildProperties.getName())
//	    			.appVersion(buildProperties.getVersion())
//	    			.time(buildProperties.get("time"))
//	    			.build();
//	    	appHealthDTO.setId(buildProperties.getName()+"-"+buildProperties.getVersion());
//	    	return appHealthDTO;
//	    }

}

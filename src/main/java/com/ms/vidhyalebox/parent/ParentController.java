package com.ms.vidhyalebox.parent;

import com.ms.shared.api.auth.ParentSignupRequestDTO;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.api.generic.GenericResponse;
import com.ms.shared.api.generic.ModalDTO;
import com.ms.shared.api.generic.Notification;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import com.ms.shared.util.util.rest.GenericController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/parent")
public class ParentController extends GenericController<ParentSignupRequestDTO, Long> {
	private final ParentServiceImpl _parentService;


	public ParentController(final ParentServiceImpl parentService
                          ) {
		_parentService = parentService;
	}

	@Override
	public IGenericService<GenericEntity, Long> getService() {
		_parentService.setAuthToken(getAuthToken());
		return _parentService;
	}

 /*   @PostMapping("/signup")
    	public ResponseEntity<ParentEntity> signup(@RequestBody ParentEntity parentEntity) {
    		ParentEntity savedUser = _parentService.signup(parentEntity);
    		return ResponseEntity.ok(savedUser);
    	}

    	@PostMapping("/login")
    	public ResponseEntity<String> login(@RequestParam String phoneNumber, @RequestParam String password) {
    		String token = _parentService.login(phoneNumber, password);
    		return ResponseEntity.ok(token);
    	}*/

    	@PostMapping("/logout")
    	public ResponseEntity<Void> logout() {
    		_parentService.logout();
    		return ResponseEntity.ok().build();
    	}

//	@PostMapping("/add-parent")
//	public GenericResponse registerParent(@Valid @RequestHeader("Authorization") @RequestBody ParentSignupRequestDTO parentSignupRequestDTO) {
//
//		List<Notification> notifications = new ArrayList<>();
//
//		if (_parentService.isEmailAlreadyExist(parentSignupRequestDTO.getParentEmail())) {
//			Notification notification = new Notification();
//			notification.setNoificationCode("401");
//			notification.setNotificationDescription("User's email address already exists");
//			notifications.add(notification);
//		}
//
//		if (_parentService.isMobileNumberExist(parentSignupRequestDTO.getParentMobile())) {
//			Notification notification = new Notification();
//			notification.setNoificationCode("401");
//			notification.setNotificationDescription("User's Phone Number already exists");
//			notifications.add(notification);
//		}
//
//		if (!notifications.isEmpty()) {
//
//			GenericResponse genericResponse = new GenericResponse();
//			genericResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
//			genericResponse.setNotifications(notifications);
//
//			return genericResponse;
//		}
//
//		GenericDTO genericDTO = _parentService.signup(parentSignupRequestDTO);
//
//		List<GenericDTO> genericDTOs = new ArrayList<>();
//		genericDTOs.add(genericDTO);
//
//		ModalDTO modalDTO = new ModalDTO();
//		modalDTO.setData(genericDTOs);
//
//		GenericResponse genericResponse = new GenericResponse();
//		genericResponse.setCode(HttpStatus.OK.getReasonPhrase());
//		genericResponse.setModalDTO(modalDTO);
//
//		return genericResponse;
//	}

}

package com.ms.vidhyalebox.orgclient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.vidhyalebox.sharedapi.OrgSignupRequestDTO;
import com.ms.vidhyalebox.util.bl.IGenericService;
import com.ms.vidhyalebox.util.domain.GenericEntity;
import com.ms.vidhyalebox.util.rest.GenericController;

@RestController
@RequestMapping("/org")
public class OrgClientController extends GenericController< OrgSignupRequestDTO ,Long> {

    private final IOrgClientService _orgClientService;

    public OrgClientController(IOrgClientService orgClientService) {
        _orgClientService = orgClientService;
    }


//    @PostMapping("/signup")
//    public ResponseEntity<OrgClientEntity> signup(@RequestBody OrgClientEntity orgClientEntity) {
//        OrgClientEntity savedOrgClient = orgClientService.signup(orgClientEntity);
//        return ResponseEntity.ok(savedOrgClient);
//    }

//
//    @PostMapping("/signup")
//    public GenericResponse registerUser(@Valid @RequestBody OrgSignupRequestDTO orgSignupRequestDTO) {
//
//        List<Notification> notifications = new ArrayList<>();
//
//        if (orgClientService.isEmailAlreadyExist(orgSignupRequestDTO.getEmailAddress())) {
//            Notification notification = new Notification();
//            notification.setNoificationCode("401");
//            notification.setNotificationDescription("User's email address already exists");
//            notifications.add(notification);
//        }
//
//        if (orgClientService.isMobileNumberExist(orgSignupRequestDTO.getIsdCode().concat(orgSignupRequestDTO.getMobileNumber()))) {
//            Notification notification = new Notification();
//            notification.setNoificationCode("401");
//            notification.setNotificationDescription("User's Phone Number already exists");
//            notifications.add(notification);
//        }
//
//        if (!notifications.isEmpty()) {
//
//            GenericResponse genericResponse = new GenericResponse();
//            genericResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
//            genericResponse.setNotifications(notifications);
//
//            return genericResponse;
//        }
//
//        GenericDTO genericDTO = orgClientService.signup(orgSignupRequestDTO);
//
//        List<GenericDTO> genericDTOs = new ArrayList<>();
//        genericDTOs.add(genericDTO);
//
//        ModalDTO modalDTO = new ModalDTO();
//        modalDTO.setData(genericDTOs);
//
//        GenericResponse genericResponse = new GenericResponse();
//        genericResponse.setCode(HttpStatus.OK.getReasonPhrase());
//        genericResponse.setModalDTO(modalDTO);
//
//        return genericResponse;
//    }
//
//
//
//
////    @PostMapping("/login")
////    public ResponseEntity<String> login(@RequestParam String emailAddress, @RequestParam String password) {
////        String token = orgClientService.login(emailAddress, password);
////        return ResponseEntity.ok(token);
////    }
//
//
//
//    @PostMapping("/signin")
//    public ResponseEntity<LoginResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) throws FatalException {
//
//        Authentication authentication = _authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
//
//        if (userDetails.isEnabled()) { // Check whether User is active or not
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//            String jwtToken = _jwtUtils.generateToken(userPrincipal.getUsername());
//
//            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//            return ResponseEntity.ok(new LoginResponseDTO(jwtToken, userDetails.getId(), roles, userDetails.isEnabled(),
//                    userDetails.isAccountNonLocked(), userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(), _jwtUtils.getExpiryDuration(), StringUtils.EMPTY));
//        }
//
//        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().message("User has been deactivated/locked !!").build();
//        return ResponseEntity.badRequest().body(loginResponseDTO);
//    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        _orgClientService.logout();
        return ResponseEntity.ok().build();
    }

    @Override
    public IGenericService<GenericEntity, Long> getService() {
        _orgClientService.setAuthToken(getAuthToken());
        return _orgClientService;
    }

}

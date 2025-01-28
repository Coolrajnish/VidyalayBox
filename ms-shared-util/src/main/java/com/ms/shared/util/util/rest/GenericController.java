package com.ms.shared.util.util.rest;

import com.github.fge.jsonpatch.JsonPatch;
import com.ms.shared.api.generic.*;
import com.ms.shared.util.util.FatalException;
//import com.ms.shared.util.util.bl.FiltersService;
import com.ms.shared.util.util.bl.IGenericService;
import com.ms.shared.util.util.domain.GenericEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.security.RolesAllowed;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 *Company:mithlaSoftech Creation Date:2024
 *@author sumit kumar
 *@version 1.0
*/

public abstract class GenericController<E extends GenericDTO, K>{

	// TODO Create a seperate enum class for these constant
	public final String GENERIC_ERROR_CODE = "400";
	public final String GENERIC_ERROR_DESC = "Error Occurred, Contact System Admin.";

	public final String GENERIC_WARNING_CODE = "199";
	public final String GENERIC_WARNING_DESC = "Warning Message Alert";

	public final String GENERIC_SUCCESS_CODE = "200";
	public final String GENERIC_SUCCESS_DESC = "Success";

//	@Autowired
//	@Getter
//	@Setter
//	FiltersService filtersService;

	@Getter
	@Setter
	public String authToken;

	public abstract IGenericService<GenericEntity, K> getService();

	@GetMapping("/{id}")
//	@RolesAllowed({"ADMIN"})
	public @ResponseBody GenericResponse getRequest(@RequestHeader("Authorization") final String token, @PathVariable final K id) throws FatalException {
		GenericResponse genericResponse = new GenericResponse();
		setAuthToken(token);
		GenericDTO genericDTO = getService().getByKey(id);
		// GenericDTO genericDTO = null;

		List<GenericDTO> genericDTOs = new ArrayList<>();
		genericDTOs.add(genericDTO);

		return getResponse(genericDTOs);
	}

	@GetMapping("/all")
//	@RolesAllowed({ "ADMIN" })
	public @ResponseBody GenericResponse getAll(@RequestHeader("Authorization") final String token) throws FatalException {
		setAuthToken(token);
		List<GenericDTO> genericDTOs = getService().getAll();
		return getResponse(genericDTOs);
	}

	@GetMapping("/list")
	@RolesAllowed({ "ADMIN" })
	public @ResponseBody GenericResponse getAllForAdmin(@RequestHeader("Authorization") final String token) throws FatalException {
		setAuthToken(token);
		List<GenericDTO> genericDTOs = getService().getAllForAdmin();
		return getResponse(genericDTOs);
	}

	@GetMapping("/all-lazy")
	@RolesAllowed({ "CLIENT", "ADMIN" })
	public @ResponseBody GenericResponse getAllLazy(@RequestHeader("Authorization") final String token) throws FatalException {
		setAuthToken(token);
		List<GenericDTO> genericDTOs = getService().getAllLazy();
		return getResponse(genericDTOs);
	}

	@PostMapping("/")
	@RolesAllowed({ "CLIENT", "ADMIN" })
	public GenericResponse add(@RequestHeader("Authorization") final String token, @Validated @RequestBody E request) throws FatalException {
		setAuthToken(token);
		GenericDTO genericDTO = getService().add(request);
		List<GenericDTO> genericDTOs = new ArrayList<>();
		genericDTOs.add(genericDTO);
		return getResponse(genericDTOs);
	}

	@PutMapping("/{id}")
	@RolesAllowed({ "CLIENT", "ADMIN" })
	public GenericResponse updateFullObject(@RequestHeader("Authorization") String token, @PathVariable K id, @Validated @RequestBody E request) throws FatalException {
		setAuthToken(token);
		request.setId(Long.toString((Long) id));
		GenericDTO genericDTO = getService().update(request);
		genericDTO.setId(id);
		List<GenericDTO> genericDTOs = new ArrayList<>();
		genericDTOs.add(genericDTO);
		return getResponse(genericDTOs);
	}

	// Read this link for more info on request
	// https://www.baeldung.com/spring-rest-json-patch
	@PatchMapping(path = "/{id}", consumes = "application/json")
	@RolesAllowed({ "CLIENT", "ADMIN" })
	public GenericResponse updatePartialObject(@RequestHeader("Authorization") String token, @PathVariable K id, @RequestBody JsonPatch patch) throws FatalException {
		setAuthToken(token);
		final GenericDTO genericDTO = getService().patch(id, patch);
		List<GenericDTO> genericDTOs = new ArrayList<>();
		genericDTOs.add(genericDTO);
		return getResponse(genericDTOs);
	}


	@DeleteMapping("/{id}")
	@RolesAllowed({"ADMIN"})
	public ResponseEntity<?> deleteRequest(@RequestHeader("Authorization") final String token, @PathVariable final K id) throws FatalException {

		try {
			setAuthToken(token);

			// Assuming you have a method like deleteById in your service
			getService().deleteById(id);

			// Return a success response
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			// Handle exceptions and return an appropriate response
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
		}
	}


	public GenericResponse getResponse(List<GenericDTO> genericDTOs) {
		GenericResponse genericResponse = new GenericResponse();

		// Pagination Info
		PaginationInfoDTO paginationInfoDTO = new PaginationInfoDTO();
		paginationInfoDTO.setCount(genericDTOs.size());
		genericResponse.setPaginationInfo(paginationInfoDTO);

		ModalDTO modalDTO = new ModalDTO();
		// ADD Filters
		modalDTO.setFilters(addFilter());

		// ADD Data
		modalDTO.setData(genericDTOs);

		// ADD Other Data
		OtherDataDTO otherDataDTO = new OtherDataDTO();
		otherDataDTO.getGenericDTO().addAll(getOtherData());
		genericResponse.setModalDTO(modalDTO);

		genericResponse.setCode(GENERIC_SUCCESS_CODE);
		genericResponse.setNotifications(null);

		return genericResponse;
	}

	public GenericResponse getResponse_Warning(List<GenericDTO> genericDTOs) {
		GenericResponse genericResponse = getResponse(genericDTOs);
		genericResponse.setCode(GENERIC_WARNING_CODE);
		genericResponse.setNotifications(getNotifications(GENERIC_WARNING_CODE, GENERIC_WARNING_DESC, null));
		return genericResponse;
	}

	public List<Notification> getNotifications(String code, String Desc, Exception ex) {
		// Notification
		List<Notification> notifications = new ArrayList<>();
		Notification notification = new Notification();
		notification.setNoificationCode(code);
		notification.setNotificationDescription(Desc);
		if (ex != null) {
			notification.setMetadata(ex.toString());
		}
		notifications.add(notification);
		return notifications;
	}

	public GenericResponse getResponse_Exception(Exception ex) {
		ModalDTO modalDTO = new ModalDTO();
		modalDTO.setData(null);

		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setCode(GENERIC_ERROR_CODE);
		// Notification
		genericResponse.setNotifications(getNotifications(GENERIC_ERROR_CODE, GENERIC_ERROR_DESC, ex));

		genericResponse.setCode(GENERIC_ERROR_CODE);
		genericResponse.setModalDTO(modalDTO);

		return genericResponse;
	}

	protected List<GenericDTO> getOtherData() {
		List<GenericDTO> genericDTO = new ArrayList<>();
		return genericDTO;
	}

	protected Set<GenericDTO> addFilter() {
		Set<GenericDTO> genericDTO = new HashSet<>();

		return genericDTO;
	}

	protected GenericResponse postGeneric(final E genericDto, HttpServletRequest... httpServeletRequest) {
		// String screenIdStr = httpServeletRequest.getParameter("screenId");

		GenericDTO genericDTO = new GenericDTO();

		try {
			IGenericService<GenericEntity, K> service = getService();
			genericDTO = service.add(genericDto);
		} catch (Exception ex) {

		}

		List<GenericDTO> genericDTOs = new ArrayList<>();
		genericDTOs.add(genericDTO);

		return getResponse(genericDTOs);

	}

	// TODO
	private GenericResponse isLoggedInUser() {
		return new GenericResponse();
	}

	/**
	 * Return the ActiveRecord result List
	 *
	 * @param service
	 *
	 * @return
	 */
//
}

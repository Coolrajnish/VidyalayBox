package com.ms.shared.util.util.bl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.FatalException;
import com.ms.shared.util.util.domain.GenericEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

@Service
public abstract class GenericService<E extends GenericEntity, K> implements IGenericService<E , K> {

	@Getter
	@Setter
	private int totalRecords;
	
	@Getter
	@Setter
	private int totalPage;
	
	@Getter
	@Setter
	private int records;
	@Getter
	@Setter
	private int pageNumber;
	
	@Getter
	@Setter	
	private Long userId;


//

	@Getter
	@Setter
	public String authToken;

	@Setter
	public List<GenericDTO> filters = new ArrayList<>();

	@Getter
	@Setter
	public static Map<String, Long> userClientMap = new HashMap<>();

	public abstract JpaRepository<GenericEntity, Object> getRepo();

	public abstract IMapperNormal getMapper();

	

	public List<GenericDTO> getFilters() {
		if (filters.isEmpty()) {
			try {
				getAll();
			} catch (FatalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return filters;
	}

	public GenericDTO getByKey(final K id) throws FatalException {
		GenericEntity genericEntity = (GenericEntity) getRepo().findById(id).orElse(null);
		// GenericDTO genericDTO = IMapper.INSTANCE.entityToDto(genericEntity);
		GenericDTO genericDTO = getMapper().entityToDto(genericEntity);

		return genericDTO;
	}

	public List<GenericDTO> getAll() throws FatalException {
		List<GenericEntity> genericEntities = (List<GenericEntity>) (Object) getRepo().findAll();
		// List<GenericDTO> genericDTOs = IMapper.INSTANCE.entityToDto(genericEntities);
		List<GenericDTO> genericDTOs = getMapper().entityToDto(genericEntities);

		filters.addAll(getMapper().getFilters());

		return genericDTOs;
	}

	public List<GenericDTO> getAllForAdmin() throws FatalException {
		List<GenericEntity> genericEntities = (List<GenericEntity>) (Object) getRepo().findAll();
		// List<GenericDTO> genericDTOs = IMapper.INSTANCE.entityToDto(genericEntities);
		List<GenericDTO> genericDTOs = getMapper().entityToDtoForAdmin(genericEntities);

		filters.addAll(getMapper().getFilters());

		return genericDTOs;
	}
	
	@Override
	public List<GenericDTO> getAllLazy() throws FatalException {
		List<GenericEntity> genericEntities = (List<GenericEntity>) (Object) getRepo().findAll();
		List<GenericDTO> genericDTOs = getMapper().entityToDtoLazy(genericEntities);

		filters.addAll(getMapper().getFilters());

		return genericDTOs;
	}


	@Override
	public void deleteById(final Object id) {
		// Add any necessary validation or logic before deleting
		// For example, checking if the entity exists
		GenericEntity genericEntity = (GenericEntity) getRepo().findById(id).orElse(null);
		if (genericEntity == null) {
			throw new EntityNotFoundException("Entity with ID " + id + " not found");
		}

		// Perform the deletion
		getRepo().deleteById(id);
	}





	/*	*//**
			 * Method getDAO.
			 * 
			 * @return IGenericDAO<E,K>
			 */
	/*
	 * public abstract <E, K> IGenericDAO<E, K> getDAO();
	 * 
	 * private Long screenId = 0L; private ZTServiceResult ztServiceResult;
	 * 
	 *//**
		 * @return the ztServiceResult
		 */
	/*
	 * public ZTServiceResult getZtServiceResult() { if (ztServiceResult == null) {
	 * ztServiceResult = new ZTServiceResult(); } return ztServiceResult; }
	 * 
	 * public Long getScreenId() { return screenId; }
	 * 
	 * public void setScreenId(Long screenId) { this.screenId = screenId; }
	 * 
	 * public abstract GenericEntity getDefaultObject();
	 * 
	 *//**
		 * Default constructor
		 */
	/*
	 * public GenericService() { super(); }
	 * 
	 *//**
		 * Gneric Service constructor with the setter of screen id.
		 * 
		 * @param paramScreenId
		 */
	/*
	 * public GenericService(Long paramScreenId) { setScreenId(paramScreenId); }
	 * 
	 * @Override
	 * 
	 * @Transactional(propagation = Propagation.REQUIRED, readOnly = true) public
	 * GenericEntity getByKey(K id) throws FatalException { return (GenericEntity)
	 * getDAO().findByKey(id); }
	 * 
	 * @Override
	 * 
	 * @Transactional(propagation = Propagation.REQUIRED) public void
	 * persist(GenericEntity entity) throws FatalException {
	 * getDAO().persist(entity); }
	 * 
	 * @Override ``
	 * 
	 * @Transactional(propagation = Propagation.REQUIRED) public void
	 * remove(GenericEntity entity) throws FatalException { getDAO().remove(entity);
	 * }
	 * 
	 * @Override
	 * 
	 * @Transactional(propagation = Propagation.REQUIRED) public void merge(E
	 * entity) throws FatalException { getDAO().merge(entity); }
	 * 
	 * @Override
	 * 
	 * @Transactional(propagation = Propagation.REQUIRED, readOnly = true) public
	 * List<GenericEntity> getAll() throws FatalException { return
	 * (List<GenericEntity>) (Object) getDAO().getAll(); }
	 * 
	 *//**
		 * Returns all the active entity.
		 * 
		 * @return
		 * @throws FatalException
		 */

	/*
	 * @Transactional(propagation = Propagation.REQUIRED, readOnly = true) public
	 * List<GenericEntity> getAllActive() throws FatalException { return
	 * (List<GenericEntity>) (Object) getDAO().getAllActive(); }
	 * 
	 *//**
		 * Process the entity on the basis of key value and return the key as -1 in case
		 * of any error. TODO Rahul K - requires proper exception handling.
		 * 
		 * @param
		 * @return Long
		 * @throws IllegalAccessException
		 * @throws InstantiationException
		 * @throws FatalException
		 */

	public E create() {
		try {
			Type sooper = getClass().getGenericSuperclass();
			Type t = ((ParameterizedType) sooper).getActualTypeArguments()[0];

			return (E) (Class.forName(t.toString()).newInstance());
		} catch (Exception e) {
			return null;
		}
	}

	public GenericDTO add(final GenericDTO dto) throws FatalException {
		GenericDTO responseDTO = new GenericDTO();
		try {
			GenericEntity finalEntity;
			finalEntity = getMapper().dtoToEntity(dto, null);
			finalEntity.setCreatedBy(getUserId());
			GenericEntity responseEntity = getRepo().save(finalEntity);
			responseDTO = getMapper().entityToDto(responseEntity);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FatalException(ex.getMessage());
		}
		return responseDTO;
	}

	public GenericDTO update(final GenericDTO dto) throws FatalException {
		GenericDTO responseDTO = new GenericDTO();
		try {
			Long id = Long.parseLong( dto.getId().toString());
			Optional<GenericEntity> optGenEntity = getRepo().findById(id);
			if (optGenEntity.isPresent()) {
				final GenericEntity finalEntity = getMapper().dtoToEntity(dto, optGenEntity.get());
				finalEntity.setUpdatedBy(getUserId());
				final GenericEntity responseEntity = getRepo().save(finalEntity);
				responseDTO = getMapper().entityToDto(responseEntity);
			} else {

				throw new EntityNotFoundException("Please connect with customer care.");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new FatalException(ex.getMessage());
		}
		return responseDTO;
	}

	//public GenericDTO patch(final Object id, final JsonPatch jsonPatch) throws FatalException
	public GenericDTO patch(final Object id, final JsonPatch jsonPatch) throws FatalException {
		GenericDTO responseDTO = new GenericDTO();
		try {
			Optional<GenericEntity> optGenEntity = getRepo().findById(id);
			if (optGenEntity.isPresent()) {
				E patchedEntity = applyPatch(jsonPatch, optGenEntity.get());
				//final GenericEntity finalEntity = getMapper().dtoToEntity(dto, optGenEntity.get());
				patchedEntity.setUpdatedBy(getUserId());
				final GenericEntity responseEntity = getRepo().save(patchedEntity);
				responseDTO = getMapper().entityToDto(responseEntity);
			} else {
				// TODO throw proper error response and log it
			}
	    }catch(Exception ex) {
		    		// TODO throw proper error response and log it
		    		ex.printStackTrace();
		    		throw new FatalException(ex.getMessage());
	    }
	    return responseDTO;
}

	@SuppressWarnings("unchecked")
	protected <E> E applyPatch(JsonPatch jsonPatch, GenericEntity genericEntity) 
			throws IllegalArgumentException, JsonPatchException, JsonProcessingException {
		E entity =  (E) genericEntity;
		JsonNode patched;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		E finalEntity = null;
		try {
			patched = jsonPatch.apply(objectMapper.convertValue(entity, JsonNode.class));
			finalEntity = (E) objectMapper.treeToValue(patched, entity.getClass());
		    
		} catch (IllegalArgumentException | JsonPatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalEntity;
	}

	/**
	 * Persist the entity.
	 * 
	 * @param entity
	 * @return
	 * @throws FatalException
	 */
	/*
	 * public Long persistEntity(E entity) throws FatalException { //
	 * entity.setKey(CurrentTimeId.nextId()); getDAO().persist(entity); return
	 * entity.getKey(); }
	 * 
	 * @Transactional(propagation = Propagation.REQUIRED, rollbackFor =
	 * Throwable.class) public Long processAllData(List<E> entityCollection) throws
	 * FatalException { try { for (E entity : entityCollection) {
	 * processData(entity);
	 * 
	 * entity.setKey(CurrentTimeId.nextId()); getDAO().persist(entity);
	 * 
	 * } return ITSConstant.PROCESS_SUCCESSFULLY; } catch (FatalException ex) {
	 * ex.printStackTrace(); return ITSConstant.PROCESS_NOT_SUCCESSFULLY;
	 * 
	 * } }
	 * 
	 * public E getEntity(E entity) { return entity; }
	 * 
	 *//**
		 * Return the ServiceResult with all active records. TODO Rahul K - Need to
		 * refine the code by defining the enums.
		 * 
		 * @return ZTServiceResult
		 */
	/*
	 * public ZTServiceResult getAllActiveResult() {
	 * 
	 * ZTServiceResult ztServiceResult; try { List<GenericEntity> lstResult =
	 * getAllActive(); ztServiceResult =
	 * getZtServiceResult().populateZTServiceResultFromResult(lstResult, screenId,
	 * getDefaultObject());
	 * 
	 * } catch (FatalException fEx) {
	 * 
	 * ztServiceResult =
	 * getZtServiceResult().populateZTServiceResultFromResult(fEx); } return
	 * ztServiceResult;
	 * 
	 * }
	 * 
	 *//**
		 * Return the ServiceResult with all active records. TODO Rahul K - Need to
		 * refine the code by defining the enums.
		 * 
		 * @return ZTServiceResult
		 */
	/*
	 * public ZTServiceResult getByLongParam(Long param) { ZTServiceResult
	 * ztServiceResult = new ZTServiceResult(); try { ztServiceResult =
	 * getZtServiceResult().populateZTServiceResultFromResult(
	 * getResultFindByLongParam(param), ITSConstant.SCREEN_ID_ZERO, null);
	 * 
	 * } catch (FatalException fEx) { ztServiceResult =
	 * getZtServiceResult().populateZTServiceResultFromResult(fEx); } return
	 * ztServiceResult; }
	 * 
	 *//**
		 * Returns the list of entity based on param. TODO Rahul K- currently its a raw
		 * code- it requires improvment in terms of performance.
		 * 
		 * @return
		 * @throws FatalException
		 */
	/*
	 * @Transactional(propagation = Propagation.REQUIRED, readOnly = true) public
	 * List<GenericEntity> getResultFindByLongParam(Long param) throws
	 * FatalException { List<GenericEntity> lstActiveEntity = new
	 * ArrayList<GenericEntity>(); List<GenericEntity> lstGenericEntity =
	 * (List<GenericEntity>) (Object) getDAO().findByKey(param);
	 * 
	 * for (GenericEntity entity : lstGenericEntity) { if (entity.getIsDel() ==
	 * false) { // lstActiveEntity.add(entity); } } return lstActiveEntity; }
	 * 
	 *//**
		 * Returns the list of entity based on param. TODO Rahul K- currently its a raw
		 * code- it requires improvment in terms of performance.
		 * 
		 * @return
		 * @throws FatalException
		 */
	/*
	 * @Transactional(propagation = Propagation.REQUIRED, readOnly = true) protected
	 * List<GenericEntity> getAllByFinYr(Long Id, int year) throws FatalException {
	 * List<GenericEntity> lstActiveEntity = new ArrayList<GenericEntity>();
	 * List<GenericEntity> lstGenericEntity = (List<GenericEntity>) (Object)
	 * getDAO().findByFinYr(year);
	 * 
	 * for (GenericEntity entity : lstGenericEntity) { if (entity.getIsDel() ==
	 * false) { // lstActiveEntity.add(entity); } } return lstGenericEntity; }
	 * 
	 *//**
		 * Returns the list of entity based on param. TODO Rahul K- currently its a raw
		 * code- it requires improvment in terms of performance.
		 * 
		 * @return
		 * @throws FatalException
		 */
	/*
	 * @Transactional(propagation = Propagation.REQUIRED, readOnly = true) public
	 * ZTServiceResult getAllActiveResultByFinYr(Long id, int year) {
	 * ZTServiceResult ztServiceResult; try { ztServiceResult =
	 * getZtServiceResult().populateZTServiceResultFromResult( (List<GenericEntity>)
	 * (Object) getAllByFinYr(id, year), ITSConstant.SCREEN_ID_ZERO,
	 * getDefaultObject());
	 * 
	 * } catch (FatalException fEx) {
	 * 
	 * ztServiceResult =
	 * getZtServiceResult().populateZTServiceResultFromResult(fEx); } return
	 * ztServiceResult;
	 * 
	 * }
	 * 
	 *//**
		 * TODO : need to look into code ...what we should return in the case of errors.
		 * 
		 * @param genericEntityColl
		 * @return
		 * @throws FatalException
		 *//*
			 * @Transactional(propagation = Propagation.REQUIRED, readOnly = true) public
			 * ZTServiceResult processAllDataResult(List<E> entity) throws FatalException {
			 * ZTServiceResult ztServiceResult = new ZTServiceResult(); Long id = null;
			 * 
			 * try {
			 * 
			 * Long result = processAllData(entity);
			 * 
			 * if (result == -1) { ztServiceResult.setServiceResultCode(-1);
			 * ztServiceResult.
			 * setServiceResultDescription("Some error Occured. Please contact System Admin."
			 * ); } else {
			 * 
			 * ztServiceResult = getAllActiveResult(); } } catch (FatalException fEx) {
			 * ztServiceResult.setServiceResultCode(-1);
			 * ztServiceResult.setServiceResultDescription(fEx.getMessage()); } return
			 * ztServiceResult;
			 * 
			 * }
			 */

}

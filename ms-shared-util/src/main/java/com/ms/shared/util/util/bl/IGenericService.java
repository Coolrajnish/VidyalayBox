package com.ms.shared.util.util.bl;

import com.github.fge.jsonpatch.JsonPatch;
import com.ms.shared.api.generic.GenericDTO;
import com.ms.shared.util.util.FatalException;
import com.ms.shared.util.util.domain.GenericEntity;

import java.util.List;

public interface IGenericService<E extends GenericEntity, K> {

	public GenericDTO getByKey(final K id) throws FatalException;

	public List<GenericDTO> getAll() throws FatalException;

	public List<GenericDTO> getAllForAdmin() throws FatalException;

	public List<GenericDTO> getAllLazy() throws FatalException;
	
	public List<GenericDTO> getFilters();

	public GenericDTO add(final GenericDTO dto) throws FatalException;

	public GenericDTO update(final GenericDTO dto) throws FatalException;

	public GenericDTO patch(final Object id, final JsonPatch jsonPatch) throws FatalException;

   public void deleteById(final Object id);

    void setAuthToken(String authToken);

    // public Long processAllData(List<E> entityCollection) throws FatalException;

	// public List<GenericDTO> getAll(String clientId) throws FatalException;

	// DAO Operations
	// public void merge(E entity) throws FatalException;

	// public GenericEntity getByKey(K id) throws FatalException;

	// public void persist(GenericEntity entity) throws FatalException;

	// public void remove(GenericEntity entity) throws FatalException;

	// public List<GenericEntity> getAll();

	// public List<GenericEntity> getAllActive();

	// public ZTServiceResult getAllActiveResult();

	// public ZTServiceResult getAllActiveResultByFinYr(Long Id, int year);

	// public ZTServiceResult getByLongParam(Long param);

	// public Long processData(E entity) throws FatalException;

	// public void setScreenId(Long screenId);

	// public abstract GenericEntity getDefaultObject();

	// public ZTServiceResult processAllDataResult(List<E> entity) throws
	// FatalException;

}

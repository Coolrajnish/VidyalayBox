package com.ms.vidhyalebox.session;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.vidhyalebox.util.bl.GenericService;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class SessionServiceImpl extends GenericService<GenericEntity, Long> implements SessionService {

	private final SessionMapper sessionMapper;

	private final SessionRepo sessionRepo;

	public SessionServiceImpl(SessionMapper sessionMapper, SessionRepo sessionRepo) {
		this.sessionMapper = sessionMapper;
		this.sessionRepo = sessionRepo;
	}

	@Override
	public JpaRepository getRepo() {
		return sessionRepo;
	}

	@Override
	public IMapperNormal getMapper() {
		return sessionMapper;
	}

	@Override
	public Page<SessionEntity> search(String orgId, String searchText, int page, int size, String sortBy,
			String sortOrder) {
		Pageable pageable = null;
		if (sortBy.isEmpty()) {
			pageable = PageRequest.of(page, size);
		} else {
			pageable = PageRequest.of(page, size,
					sortOrder.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
		}
		if (!orgId.isEmpty()) {
			return sessionRepo.search(orgId, searchText, pageable);
		} else {
			return sessionRepo.findAll(pageable);
		}
	}

	@Override
	public SessionEntity save(SessionDTO dto) {

		SessionEntity entity = (SessionEntity) sessionMapper.dtoToEntity(dto);
		entity = sessionRepo.save(entity);

		return entity;
	}
	
	@Transactional
	@Override
	public SessionEntity modify(SessionDTO sDTO) {
		SessionEntity entity = null;
		try {
			entity = sessionRepo.findById((Long) sDTO.getId()).get();
			entity = (SessionEntity) sessionMapper.dtoToEntity(sDTO, entity);
			entity =  sessionRepo.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	logger.error("error -->", e.getStackTrace());
		}
		return entity;
	}
}

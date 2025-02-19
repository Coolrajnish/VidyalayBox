package com.ms.vidhyalebox.fees;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.vidhyalebox.Class.ClassEntity;
import com.ms.vidhyalebox.Class.ClassRepo;
import com.ms.vidhyalebox.feesamount.FeesAmountDTO;
import com.ms.vidhyalebox.feesamount.FeesAmountEntity;
import com.ms.vidhyalebox.feesinstalment.FeesInstallmentDTO;
import com.ms.vidhyalebox.feesinstalment.FeesInstallmentEntity;
import com.ms.vidhyalebox.feestype.FeesTypeRepo;
import com.ms.vidhyalebox.orgclient.IOrgClientRepo;
import com.ms.vidhyalebox.orgclient.OrgClientEntity;
import com.ms.vidhyalebox.session.SessionRepo;
import com.ms.vidhyalebox.sharedapi.generic.GenericDTO;
import com.ms.vidhyalebox.util.bl.IMapperNormal;
import com.ms.vidhyalebox.util.domain.GenericEntity;

@Service
public class FeesMapperNormal implements IMapperNormal {

	@Autowired
	IOrgClientRepo orgRepo;

	@Autowired
	FeesTypeRepo feesTRepo;

	@Autowired
	ClassRepo cRepo;

	@Autowired
	SessionRepo sessionRepo;

	@Override
	public GenericEntity dtoToEntity(GenericDTO genericDto, GenericEntity genericEntity) {
		FeesEntity entity = (genericEntity == null) ? new FeesEntity() : (FeesEntity) genericEntity;

		FeesDTO feesDTO = (FeesDTO) genericDto;

		// Update FeeDueCharges only if it's not null
		if (feesDTO.getFeeDueCharges() != null) {
			entity.setFeeDueCharges(feesDTO.getFeeDueCharges());
		}

		// Update FeesName only if it's not null
		if (feesDTO.getFeeName() != null) {
			entity.setFeesName(feesDTO.getFeeName());
		}

		// Update FeeDueChargesType only if it's not null
		if (feesDTO.getFeeDueChargesType() != null) {
			entity.setFeeDueChargesType(feesDTO.getFeeDueChargesType());
		}

		// Update FeeDueDate only if it's not null
		if (feesDTO.getFeeDueDate() != null) {
			entity.setFeeDueDate(feesDTO.getFeeDueDate());
		}

		// Only update the school if orgUniqId is not null
		if (feesDTO.getOrgUniqId() != null) {
			Optional<OrgClientEntity> school = orgRepo.findByOrgUniqId(feesDTO.getOrgUniqId());
			school.ifPresent(entity::setSchool);
		}

		// Only update applicable classes if classId list is not null
		if (feesDTO.getClassId() != null) {
			List<ClassEntity> feesClass = feesDTO.getClassId().stream()
					.map(cid -> cRepo.findById(cid)
							.orElseThrow(() -> new RuntimeException("Class not found for ID: " + cid)))
					.collect(Collectors.toList());
			entity.setApplicableClasses(feesClass);
		}

		final double[] totalAmount = { 0.0 };
		final double[] compulsoryAmount = { 0.0 };

		// Update FeeAmounts only if feesAmunt list is not null
		if (feesDTO.getFeesAmunt() != null) {
			List<FeesAmountEntity> feesAmountEntities = feesDTO.getFeesAmunt().stream().map(fd -> {
				FeesAmountEntity feesAmountEntity = new FeesAmountEntity();

				// Update FeesAmount only if it's not null
				if (fd.getFeesAmount() != null) {
					feesAmountEntity.setFeesAmount(fd.getFeesAmount());
				}

				// Update FeeType only if it's not null
				if (fd.getFeeTypeId() != null) {
					feesAmountEntity.setFeeType(feesTRepo.findById(fd.getFeeTypeId())
							.orElseThrow(() -> new RuntimeException("FeeType not found for ID: " + fd.getFeeTypeId())));
				}

				// Update FeeTypes only if it's not null
				if (fd.getFeeTypes() != null) {
					feesAmountEntity.setFeeTypes(fd.getFeeTypes());
				}

				// Update amounts
				if ("COMPULSORY".equals(fd.getFeeTypes()) && fd.getFeesAmount() != null) {
					compulsoryAmount[0] += Double.parseDouble(fd.getFeesAmount());
				}
				if (fd.getFeesAmount() != null) {
					totalAmount[0] += Double.parseDouble(fd.getFeesAmount());
				}

				// Update School only if orgUniqId is not null
				if (fd.getOrgUniqId() != null) {
					Optional<OrgClientEntity> orgClient = orgRepo.findByOrgUniqId(fd.getOrgUniqId());
					orgClient.ifPresent(feesAmountEntity::setSchool);
				}

				feesAmountEntity.setFees(entity);
				return feesAmountEntity;
			}).collect(Collectors.toList());

			entity.setSession(sessionRepo.findById(feesDTO.getSessionId()).get());
			entity.setTotalAmount(String.valueOf(totalAmount[0]));
			entity.setCompulsoryAmount(String.valueOf(compulsoryAmount[0]));
			entity.setFeeAmounts(feesAmountEntities);
		}

		// Update FeesInstallments only if the list is not null
		if (feesDTO.getFeesInstallment() != null) {
			List<FeesInstallmentEntity> feesInstallmentEntities = feesDTO.getFeesInstallment().stream().map(fi -> {
				FeesInstallmentEntity feesInstallmentEntity = new FeesInstallmentEntity();

				// Update FeeDueCharges only if it's not null
				if (fi.getFeeDueCharges() != null) {
					feesInstallmentEntity.setFeeDueCharges(fi.getFeeDueCharges());
				}

				// Update FeeDueChargesType only if it's not null
				if (fi.getFeeDueChargesType() != null) {
					feesInstallmentEntity.setFeeDueChargesType(fi.getFeeDueChargesType());
				}

				// Update FeeInstallmentDueDate only if it's not null
				if (fi.getFeeInstallmentDueDate() != null) {
					feesInstallmentEntity.setFeeInstallmentDueDate(fi.getFeeInstallmentDueDate());
				}

				// Update FeeInstallmentName only if it's not null
				if (fi.getFeeInstallmentName() != null) {
					feesInstallmentEntity.setFeeInstallmentName(fi.getFeeInstallmentName());
				}

				// Update School only if orgUniqId is not null
				if (fi.getOrgUniqId() != null) {
					Optional<OrgClientEntity> orgClientInstallment = orgRepo.findByOrgUniqId(fi.getOrgUniqId());
					orgClientInstallment.ifPresent(feesInstallmentEntity::setSchool);
				}

				feesInstallmentEntity.setFeesInstallment(entity);
				return feesInstallmentEntity;
			}).collect(Collectors.toList());

			entity.setFeeInstallment(feesInstallmentEntities);
		}

		return entity;
	}

	@Override
	public GenericDTO entityToDto(GenericEntity genericEntity) {
		// Create a new FeesDTO
		FeesDTO feesDTO = new FeesDTO();
		FeesEntity feesEntity = (FeesEntity) genericEntity;

		// Set basic fields from FeesEntity to FeesDTO
		feesDTO.setFeeDueCharges(feesEntity.getFeeDueCharges());
		feesDTO.setFeeName(feesEntity.getFeesName());
		feesDTO.setFeeDueChargesType(feesEntity.getFeeDueChargesType());
		feesDTO.setFeeDueDate(feesEntity.getFeeDueDate());
		// feesDTO.setTotalAmount(feesEntity.getTotalAmount());
		// feesDTO.setCompulsoryAmount(feesEntity.getCompulsoryAmount());

		// Map applicableClasses (List<ClassEntity> -> List<ClassDTO>)
//		    List<ClassDTO> classDTOList = new ArrayList<>();
//		    for (ClassEntity classEntity : feesEntity.getApplicableClasses()) {
//		        ClassDTO classDTO = new ClassDTO();
//		        classDTO.setClassId(classEntity.getId());
//		        classDTO.setClassName(classEntity.getClassName()); // Adjust this according to your ClassEntity structure
//		        // Populate any other fields of ClassDTO as needed
//		        classDTOList.add(classDTO);
//		    }
//		    feesDTO.setApplicableClasses(classDTOList);

		// Map feeAmounts (List<FeesAmountEntity> -> List<FeesAmountDTO>)
		List<FeesAmountDTO> feesAmountDTOList = new ArrayList<>();
		for (FeesAmountEntity feesAmountEntity : feesEntity.getFeeAmounts()) {
			FeesAmountDTO feesAmountDTO = new FeesAmountDTO();
			feesAmountDTO.setFeesAmount(feesAmountEntity.getFeesAmount());
			feesAmountDTO.setFeeTypes(feesAmountEntity.getFeeTypes());
			feesAmountDTO.setFeeTypeId(feesAmountEntity.getFeeType().getId()); // Assuming you want the FeeType ID
			feesAmountDTO.setOrgUniqId(feesAmountEntity.getSchool().getOrgUniqId()); // Assuming OrgClientEntity has
																						// orgUniqId
			// Populate any other fields of FeesAmountDTO as needed
			feesAmountDTOList.add(feesAmountDTO);
		}
		feesDTO.setFeesAmunt(feesAmountDTOList);

		// Map feeInstallment (List<FeesInstallmentEntity> -> List<FeesInstallmentDTO>)
		List<FeesInstallmentDTO> feesInstallmentDTOList = new ArrayList<>();
		for (FeesInstallmentEntity feesInstallmentEntity : feesEntity.getFeeInstallment()) {
			FeesInstallmentDTO feesInstallmentDTO = new FeesInstallmentDTO();
			feesInstallmentDTO.setFeeDueCharges(feesInstallmentEntity.getFeeDueCharges());
			feesInstallmentDTO.setFeeDueChargesType(feesInstallmentEntity.getFeeDueChargesType());
			feesInstallmentDTO.setFeeInstallmentDueDate(feesInstallmentEntity.getFeeInstallmentDueDate());
			feesInstallmentDTO.setFeeInstallmentName(feesInstallmentEntity.getFeeInstallmentName());
			feesInstallmentDTO.setOrgUniqId(feesInstallmentEntity.getSchool().getOrgUniqId()); // Assuming
																								// OrgClientEntity has
																								// orgUniqId
			// Populate any other fields of FeesInstallmentDTO as needed
			feesInstallmentDTOList.add(feesInstallmentDTO);
		}
		feesDTO.setFeesInstallment(feesInstallmentDTOList);

		return feesDTO;

	}
}

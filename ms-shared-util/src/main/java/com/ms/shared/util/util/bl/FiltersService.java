//package com.ms.shared.util.util.bl;
//
///*
// *Company:mithlaSoftech Creation Date:2024
// *@author sumit kumar
// *@version 1.0
// */
//import com.ms.shared.api.generic.CalenderDTO;
//import com.ms.shared.api.generic.FilterDTO;
//import com.ms.shared.api.generic.GenericDTO;
//import com.ms.shared.util.util.Constants;
//import lombok.Data;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Service
//public final class FiltersService {
//
//	List<GenericDTO> filterDTOLst = new ArrayList<>();
//
//	public void addYearQuarterMonthFilter(final boolean enableYearFiler, final boolean enableQuarterFiler, boolean enableMonthFiler) {
//		List<GenericDTO> genericDTOLst = new ArrayList<>();
//
//		CalenderDTO calenderDTO = new CalenderDTO();
//		FilterDTO filterDto = new FilterDTO();
//
//		if (enableYearFiler) {
//			// APPEND YEAR FILTER
//			filterDto.setFilterKey(Constants.FILTER_YEAR);
//			filterDto.setFilterName("Year");
//			filterDto.setSortingOrder(100);
//			filterDto.setApplyToColumn("salesYear");
//			filterDto.setGenericDTO((List<Object>) ((Object) calenderDTO.getYears()));
//			Constants.getFilterMAP().put(Constants.FILTER_YEAR, filterDto);
//		}
//
//		if (enableQuarterFiler) {
//			// APPEND Quarter FILTER
//			filterDto = new FilterDTO();
//			filterDto.setFilterKey(Constants.FILTER_QUARTER);
//			filterDto.setFilterName("Quarter");
//			filterDto.setSortingOrder(200);
//			filterDto.setApplyToColumn("");
//			filterDto.setGenericDTO((List<Object>) ((Object) calenderDTO.getQuarter()));
//
//			Constants.getFilterMAP().put(Constants.FILTER_QUARTER, filterDto);
//		}
//
//		if (enableMonthFiler) {
//			// APPEND Month FILTER
//			filterDto = new FilterDTO();
//			filterDto.setFilterKey(Constants.FILTER_MONTH);
//			filterDto.setFilterName("Month");
//			filterDto.setSortingOrder(300);
//			filterDto.setApplyToColumn("salesMonth");
//			filterDto.setGenericDTO((List<Object>) ((Object) calenderDTO.getMonths()));
//
//			Constants.getFilterMAP().put(Constants.FILTER_MONTH, filterDto);
//		}
//
//		getFilterDTOLst().addAll(genericDTOLst);
//	}
//
//	/*
//	 * public void addSalesChannelFilter() { // APPEND Month FILTER FilterDTO
//	 * filterDto = new FilterDTO(); filterDto.setFilterName("Sales Channel");
//	 * filterDto.setApplyToColumn("salesChannel"); try { for (GenericDTO genericDTO
//	 * : marketPlacesServiceImpl.getAll()) { // TODO SK consider to loop here or in
//	 * mapper to save 2 times looping } } catch (FatalException e) { // TODO
//	 * Auto-generated catch block e.printStackTrace(); }
//	 * getFilterDTOLst().add(filterDto); }
//	 */
//
//}

package com.ms.shared.api.generic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonRootName(value = "calFilter")
public class CalenderDTO extends GenericDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<FilterDataDTO> years = new ArrayList<>();
	public List<FilterDataDTO> quarter = new ArrayList<>();
	public List<FilterDataDTO> months = new ArrayList<>();

	public CalenderDTO() {
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);

		// YEars
		for (Integer yr = 2016; yr <= currentYear; yr++) {
			this.years.add(new FilterDataDTO(yr, yr.toString(), ""));
		}

		// Quarter
		this.quarter.add(new FilterDataDTO(1, "Q-1", "Quarter-1"));
		this.quarter.add(new FilterDataDTO(2, "Q-2", "Quarter-2"));
		this.quarter.add(new FilterDataDTO(3, "Q-3", "Quarter-3"));

		// Month
		this.months.add(new FilterDataDTO(1, "Jan", "January"));
		this.months.add(new FilterDataDTO(2, "Feb", "Febuary"));
		this.months.add(new FilterDataDTO(3, "Mar", "March"));

		this.months.add(new FilterDataDTO(4, "Apr", "April"));
		this.months.add(new FilterDataDTO(5, "May", "May"));
		this.months.add(new FilterDataDTO(6, "Jun", "June"));

		this.months.add(new FilterDataDTO(7, "Jul", "July"));
		this.months.add(new FilterDataDTO(8, "Aug", "August"));
		this.months.add(new FilterDataDTO(9, "Sep", "September"));

		this.months.add(new FilterDataDTO(10, "Oct", "October"));
		this.months.add(new FilterDataDTO(11, "Nov", "November"));
		this.months.add(new FilterDataDTO(12, "Dec", "December"));

	}
}

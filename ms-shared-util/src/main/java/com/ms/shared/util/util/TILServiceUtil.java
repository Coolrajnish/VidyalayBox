package com.ms.shared.util.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ms.shared.api.generic.GenericDTO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class TILServiceUtil {

	

	/*
	 * This method will generate unique id universally
	 */
	public static String getReceiptId() {
		return getUniqueId();
	}

	private static String getUniqueId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static BigDecimal getConvertedAmountForPaymentGateway(final BigDecimal subscriptionCharges) {
		return subscriptionCharges.multiply(BigDecimal.valueOf(100));
	}

	public static Date convertStringToDate(String inputDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		Date result = null;
		try {
			result = formatter.parse(inputDate);
		} catch (ParseException ex) {
			log.error(String.format("InputDate is not in correct format: %s", inputDate), ex);
		}
		return result;
	}

	public static Date convertStringToDate(String inputDate, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		Date result = null;
		try {
			result = formatter.parse(inputDate);
		} catch (ParseException ex) {
			log.error(String.format("InputDate is not in correct format: %s", inputDate), ex);
		}
		return result;
	}

	public static OffsetDateTime convertStringToOffsetDateTime(String inputDate, String format) {
		Date tempDate = convertStringToDate(inputDate, format);
		return tempDate.toInstant().atOffset(ZonedDateTime.now().getOffset());
	}

	public static String convertDateToString(Date date, String format) {
		if (date == null) {
			return new StringBuilder().toString();
		}
		DateFormat dateFormat = new SimpleDateFormat(format);
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public static String generateReferralCode(final String firstName, final String lastName, final String verificationInfo) {

		int n = 6;
		// chose a Character random from this String
		String AlphaNumericString = firstName.toUpperCase() + lastName.toUpperCase() + verificationInfo;

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index
					= (int) (AlphaNumericString.length()
					* Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString
					.charAt(index));
		}

		return sb.toString();
	}

	public static String generateOTP() {

		String numericString = "0987654321";
		StringBuilder sb = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {

			// generate a random number between
			// 0 to numericString variable length
			int index = (int) (numericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(numericString.charAt(index));
		}

		return sb.toString();

	}

	public static boolean isValidDateTime(String input) {
		if (StringUtils.hasText(input)) {
			return false;
		}

		try {
			DateUtils.parseDateStrictly(input, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException | DateTimeParseException e) {
			return false;
		}

		return true;
	}

	public static List<Long> commaSeparatedIdsToLongList(String input) {
		List<Long> idList = new ArrayList<>();
		if (StringUtils.hasText(input)) {
			String[] parts = input.split(",");
			for (String part : parts) {
				Long id = Long.parseLong(part.trim());
				idList.add(id);
			}
		}
		return idList;
	}

	public static List<Long> convertStringListToLongList(List<String> stringList) {
		List<Long> longList = new ArrayList<>();
		for (String string : stringList) {
			longList.add(Long.parseLong(string));
		}
		return longList;
	}

	public static String convertToString(Integer number) {
		if (number != null) {
			return number.toString();
		} else {
			return null; // or an alternative value or handling for null case
		}
	}

	public static String convertToString(Long number) {
		if (number != null) {
			return String.valueOf(number);
		} else {
			return null; // or an alternative value or handling for null case
		}
	}

	public static String convertToString(Double value) {
		if (value != null) {
			return String.valueOf(value);
		} else {
			return null; // or any default value you prefer
		}
	}

	public static String convertToString(BigDecimal value) {
		if (value != null) {
			return value.toString();
		} else {
			return null; // or any other default value or handling you prefer
		}
	}

	public static Long convertToLong(String input) {
		if (input != null) {
			try {
				return Long.parseLong(input);
			} catch (NumberFormatException e) {
				// If the string is not a valid long, return null
				return null;
			}
		} else {
			// If the input string is null, return null
			return null;
		}
	}

	public static Double convertToDouble(String input) {
		if (input != null) {
			try {
				return Double.parseDouble(input);
			} catch (NumberFormatException e) {
				// If the string is not a valid double, return null
				return null;
			}
		} else {
			// If the input string is null, return null
			return null;
		}
	}

	public static Double convertToDouble(Integer input) {
		if (input == null) {
			return null;
		}

		// Convert Integer to Double and return
		return input.doubleValue();
	}
	public static BigDecimal convertToBigDecimal(Double input) {
		if (input == null) {
			return null;
		}

		// Convert Double to BigDecimal and return
		return new BigDecimal(input.toString());
	}

	public static Integer convertToInteger(String input) {
		if (input != null) {
			try {
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				// If the string is not a valid integer, return null
				return null;
			}
		} else {
			// If the input string is null, return null
			return null;
		}
	}

	public static BigDecimal convertToBigDecimal(String str) {
		if (str == null || str.isEmpty()) {
			return BigDecimal.ZERO;
		}

		try {
			return new BigDecimal(str);
		} catch (NumberFormatException e) {
			return BigDecimal.ZERO;
		}
	}

	public static BigInteger convertToBigInteger(String input) {
		if (input == null) {
			return null;
		}

		return new BigInteger(input);
	}

	public static String convertToString(BigInteger input) {
		if (input == null) {
			return null;
		}

		return input.toString();
	}

	public static String printObjectAsJson(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();

		String json = null;
		try {
			json = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String entityToJsonString(GenericDTO genericDTO) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper.writeValueAsString(genericDTO);
	}

	public static Date parseInputDate(String userInput) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			return sdf.parse(userInput);
		} catch (ParseException e) {
			// Handle the exception or rethrow it as needed
			throw new IllegalArgumentException("Error parsing the date. Please enter a valid date format.", e);
		}
	}

	public static String convertDateFormat(String inputString) {
		try {

			SimpleDateFormat inputFormat = new SimpleDateFormat("d-MM-yyyy HH:mm:ss");

			Date date = inputFormat.parse(inputString);

			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

			return outputFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace(); // Handle the exception as needed
			return null; // Return null in case of an error
		}
	}


	public static LocalDateTime convertStringToTradeTime(String inputString) {
		if (inputString == null) {
			return null;
		}

		try {
			return LocalDateTime.parse(inputString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		} catch (Exception e) {
			// Handle the parsing exception, such as invalid format
			e.printStackTrace(); // or log the exception
			return null;
		}
	}
}


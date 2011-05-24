package mtcls.server.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mtcls.common.model.AppProperty;
import mtcls.common.model.Criteria;
import mtcls.common.model.app.DataTypeEnum;

public class CriteriaHelper {
	public static final String MASK_DATE_SHORT = "yyyy/MM/dd";
	public static List<Criteria> toFetchCriteria(List<AppProperty> properties){
		return toFetchCriteria(properties, false);
	}
	public static List<Criteria> toFetchCriteria(List<AppProperty> properties, boolean isToDateEnabled){
		List<Criteria> retval = null;
		if(properties == null || properties.size()<=0){
			return retval;
		}
		retval = new ArrayList<Criteria>();
		for (AppProperty appProperty : properties) {
			Criteria criteria = fromFetchProperty(appProperty, isToDateEnabled);
			if(criteria != null){
				retval.add(criteria);
			}
		}
		return retval;
	}
	
	public static Criteria fromFetchProperty(AppProperty property, boolean isToDateEnabled){
		Criteria retval = null;
		if(property == null){
			return retval;
		}
		String propertyName = property.getDaoPropertyName();
		String propertyType = property.getDataType();
		if(propertyType == null || "".equals(propertyType)){
			return retval;
		}
		DataTypeEnum dataTypeEnum = DataTypeEnum.fromString(propertyType);
		if(dataTypeEnum == null){
			return retval;
		}
		String value = null;
		if(property.getValue()!=null){
			value = String.valueOf(property.getValue());
		}
		if(DataTypeEnum.STRING.equals(dataTypeEnum)){
			String fetchValue = (value!=null) ? "'%" + value + "%'" : value;
			String operator = (value != null) ? "LIKE" : "IS NOT NULL"; 
			retval = new Criteria(propertyName, operator, fetchValue);
			return retval;
		}
		if(value==null){
			return retval;
		}
		if(DataTypeEnum.NUMBER.equals(dataTypeEnum) || DataTypeEnum.FLOAT.equals(dataTypeEnum)){
			retval = new Criteria(propertyName, "=", value);
		}
		if(DataTypeEnum.DATE.equals(dataTypeEnum)){
			String regexPattern = property.getRegexPattern();
			Object objValue = property.getValue();
			Date dateValue = null;
			if(objValue instanceof Date){
				dateValue = (Date)dateValue;
			}
			try{
				SimpleDateFormat format = new SimpleDateFormat(regexPattern);
				dateValue = format.parse(value);
			}catch(Exception ignoreFailed){
				
			}
			if(isToDateEnabled && dateValue != null){
				retval = createToDateFetchCriteria(propertyName, dateValue);
			}else{
				retval = createDateFetchCriteria(propertyName, dateValue);
			}
			return retval;

		}
		return retval;
	}
	
	public static Criteria createDateFetchCriteria(String propertyName, Date dateValue){
		Criteria retval = null;
		if(propertyName == null || dateValue == null){
			return retval;
		}
		String value = toShortDateMask(dateValue);
		if(value == null){
			return retval;
		}
		retval = new Criteria(propertyName, "=", value);
		return retval;
	}

	public static Criteria createToDateFetchCriteria(String propertyName, Date dateValue){
		Criteria retval = null;
		if(propertyName == null || dateValue == null){
			return retval;
		}
		String value =  createToDateString(dateValue);
		if(value == null){
			return retval;
		}
		retval = new Criteria(propertyName, "=", value);
		return retval;
	}
	
	public static String toShortDateMask(Date dateValue){
		String retval = null;
		if(dateValue == null){
			return retval;
		}
		SimpleDateFormat format = new SimpleDateFormat(MASK_DATE_SHORT);
		retval = format.format(dateValue);
		return retval;
	}
	
	public static String createToDateString(Date dateValue){
		String retval = null;
		if(dateValue == null){
			return retval;
		}
		SimpleDateFormat format = new SimpleDateFormat(MASK_DATE_SHORT);
		String value = format.format(dateValue);
		retval = "to_date('" + value + "', '" + MASK_DATE_SHORT + "')";
		return retval;
	}
	
}

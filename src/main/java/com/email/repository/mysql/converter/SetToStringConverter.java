package com.email.repository.mysql.converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.util.CollectionUtils;

/** @author Sonu Kumar, created on 20-Jan-2019 */
public class SetToStringConverter implements AttributeConverter<Set<Long>, String> {

  /**
   * Converts the value stored in the entity attribute into the data representation to be stored in
   * the database.
   *
   * @param attribute the entity attribute value to be converted
   * @return the converted data to be stored in the database column
   */
  @Override
  public String convertToDatabaseColumn(Set<Long> attribute) {
    if (CollectionUtils.isEmpty(attribute)) {
      return "";
    }
    List<String> stringList = attribute.stream().map(String::valueOf).collect(Collectors.toList());
    return StringUtils.join(stringList, ',');
  }

  /**
   * Converts the data stored in the database column into the value to be stored in the entity
   * attribute. Note that it is the responsibility of the converter writer to specify the correct
   * <code>dbData</code> type for the corresponding column for use by the JDBC driver: i.e.,
   * persistence providers are not expected to do such type conversion.
   *
   * @param dbData the data from the database column to be converted
   * @return the converted value to be stored in the entity attribute
   */
  @Override
  public Set<Long> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.equals("")) {
      return new HashSet<>();
    }
    String[] words = dbData.split(",");
    return Arrays.stream(words).map(Long::parseLong).collect(Collectors.toSet());
  }
}

package com.shine.core.security.jpa;

import com.shine.core.security.PermissionValueType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Converter(autoApply = true)
public class PermissionValueTypeConvertor implements AttributeConverter<PermissionValueType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PermissionValueType attribute) {
        return attribute.getType();
    }

    @Override
    public PermissionValueType convertToEntityAttribute(Integer dbData) {
        return PermissionValueType.fromType(dbData);
    }
}

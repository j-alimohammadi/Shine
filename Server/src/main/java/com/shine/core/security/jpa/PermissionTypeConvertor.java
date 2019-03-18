package com.shine.core.security.jpa;

import com.shine.core.security.PermissionType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Converter(autoApply = true)
public class PermissionTypeConvertor implements AttributeConverter<PermissionType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PermissionType attribute) {
        return attribute.getType();
    }

    @Override
    public PermissionType convertToEntityAttribute(Integer dbData) {
        return null;
    }
}

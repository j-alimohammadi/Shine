package com.shine.core.security.jpa;

import com.shine.core.security.PermissionType;

import javax.persistence.AttributeConverter;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

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

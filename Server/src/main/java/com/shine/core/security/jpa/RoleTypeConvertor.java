package com.shine.core.security.jpa;

import com.shine.core.security.RoleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Converter(autoApply = true)
public class RoleTypeConvertor implements AttributeConverter<RoleType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(RoleType roleType) {
        return roleType.getType();
    }

    @Override
    public RoleType convertToEntityAttribute(Integer dbData) {
        return RoleType.fromType(dbData);
    }

}

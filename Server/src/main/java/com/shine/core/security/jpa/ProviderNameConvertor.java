package com.shine.core.security.jpa;

import com.shine.core.security.service.OAuthProvider;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Converter(autoApply = true)
public class ProviderNameConvertor implements AttributeConverter<OAuthProvider, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OAuthProvider attribute) {
        return attribute.getType();
    }

    @Override
    public OAuthProvider convertToEntityAttribute(Integer dbData) {
        return OAuthProvider.fromType(dbData);
    }
}

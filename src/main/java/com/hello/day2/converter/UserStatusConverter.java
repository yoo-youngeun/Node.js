package com.hello.day2.converter;

import com.hello.day2.enumclass.UserStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {

    @Override
    public String convertToDatabaseColumn(UserStatus attribute) { // 엔티티->데이터베이스
        if (Objects.isNull(attribute)) {
            throw new NullPointerException("Enum Converting String - OrderStatus is null");
        }

        return attribute.toString();
    }

    @Override
    public UserStatus convertToEntityAttribute(String dbData) { // 데이터베이스->엔티티
        return UserStatus.valueOf(dbData);
    }
}

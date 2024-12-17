package com.message.myMiddlewareMQ.mapper;

import com.message.myMiddlewareMQ.dto.PartnerDTO;
import com.message.myMiddlewareMQ.model.PartnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartnerMapper {
    PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);

    PartnerDTO toDto(PartnerEntity entity);
    PartnerEntity toEntity(PartnerDTO dto);
}

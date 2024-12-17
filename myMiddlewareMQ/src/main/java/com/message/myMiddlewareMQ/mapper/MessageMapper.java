package com.message.myMiddlewareMQ.mapper;

import com.message.myMiddlewareMQ.dto.MessageDTO;
import com.message.myMiddlewareMQ.model.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO toDto(MessageEntity entity);
    MessageEntity toEntity(MessageDTO dto);
}

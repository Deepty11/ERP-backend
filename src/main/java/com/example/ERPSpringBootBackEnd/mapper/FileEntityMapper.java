package com.example.ERPSpringBootBackEnd.mapper;

import com.example.ERPSpringBootBackEnd.dto.requestDto.FileEntityDto;
import com.example.ERPSpringBootBackEnd.model.FileEntity;

import java.util.Base64;
import java.util.Objects;

public class FileEntityMapper {
    public static FileEntity toFileEntity(FileEntityDto fileEntityDto) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(fileEntity.getId());
        fileEntity.setFileName(fileEntityDto.getFileName());
        fileEntity.setDocument(Base64.getDecoder().decode(fileEntityDto.getData()));

        return fileEntity;

    }

    public static FileEntityDto toFileEntityDto(FileEntity fileEntity) {
        return Objects.nonNull(fileEntity) ? new FileEntityDto(fileEntity) : null;
    }
}

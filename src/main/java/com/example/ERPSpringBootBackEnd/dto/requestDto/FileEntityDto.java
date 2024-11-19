package com.example.ERPSpringBootBackEnd.dto.requestDto;

import com.example.ERPSpringBootBackEnd.model.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEntityDto {
    private long id;
    private String fileName;
    private String data;

    public FileEntityDto(FileEntity fileEntity) {
        this.id = fileEntity.getId();
        this.fileName = fileEntity.getFileName();
        this.data = Base64.getEncoder().encodeToString(fileEntity.getDocument());
    }
}

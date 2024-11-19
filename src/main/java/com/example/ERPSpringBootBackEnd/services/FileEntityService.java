package com.example.ERPSpringBootBackEnd.services;

import com.example.ERPSpringBootBackEnd.model.FileEntity;
import com.example.ERPSpringBootBackEnd.repositories.FileEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileEntityService {
    @Autowired
    private FileEntityRepository repository;

    public FileEntity save(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setDocument(file.getBytes());

        return repository.save(fileEntity);
    }

    public FileEntity getFileById(long id) {
        Optional<FileEntity> optional = repository.findById(id);
        if(optional.isEmpty()) {
            return null;
        }

        return optional.get();
    }
}

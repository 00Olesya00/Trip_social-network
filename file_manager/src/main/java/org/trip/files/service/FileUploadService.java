package org.trip.files.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.trip.files.entity.Extensions;
import org.trip.files.entity.Images;
import org.trip.files.repository.ExtensionsRepository;
import org.trip.files.repository.ImagesRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class FileUploadService {

    @Autowired
    ImagesRepository imagesRepository;
    @Autowired
    ExtensionsRepository extensionsRepository;
    private static final long MAX_FILE_SIZE = 50 * 1024 * 1024; // 20MB

    private final String uploadFolder = "upload\\"; // Specify the folder path where you want to save the files

    public void uploadFile(HttpServletRequest request) throws IOException, ServletException {
        // Check if file has write access
        checkWriteAccess(uploadFolder);
        // Save the file to the upload folder
        saveFile(request);
    }

    public void checkWriteAccess(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            throw new IllegalArgumentException("Folder does not exist: " + folderPath);
        }
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("Path is not a folder: " + folderPath);
        }
        if (!folder.canWrite()) {
            throw new IllegalArgumentException("No write access to folder: " + folderPath);
        }
        log.info("You can write it");
    }

    private void saveFile(HttpServletRequest request) throws IOException, ServletException {
        // Create the upload folder if it doesn't exist
        for (Part part : request.getParts()){
            String contentType = part.getContentType();
            String parameterName = part.getName();
            String filename = part.getSubmittedFileName();
            String extension = filename.substring(filename.lastIndexOf("."));
            if (part.getSize() > MAX_FILE_SIZE) {
                throw new IllegalArgumentException("File size exceeds the maximum limit of 50MB");
            } else if (extensionsRepository.findByExtension(extension) != null) {
                throw new IllegalArgumentException("You cannot upload file with extension: " + extension);
            }
            log.info("Content type: " + contentType);
            log.info("Parameter name: " + parameterName);
            log.info("File name: " + filename);

            try (InputStream inputStream = part.getInputStream()){
                byte[] content = StreamUtils.copyToByteArray(inputStream);
                Files.write(Path.of(uploadFolder + filename), content);
                log.info("file has been stored");
            }
            Images image = new Images(uploadFolder + filename, filename);
            Extensions extensions = new Extensions();
            extensions.setExtension(extension);
            extensions.setDescription(filename);
            extensions.setActive(1L);
            image.setExtensionId(extensions);
            imagesRepository.save(image);
        }
    }

    private void deleteFile(Long id){
        Images images = imagesRepository.getReferenceById(id);
        imagesRepository.delete(images);
    }
}

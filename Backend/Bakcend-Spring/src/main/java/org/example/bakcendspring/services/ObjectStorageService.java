package org.example.bakcendspring.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ObjectStorageService {
    private final S3Client s3Client;

    @Value("${app.bucket-name}")
    private String BUCKET_NAME;

    public void sendRequest(String fileName, MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(fileName)
                    .build();


            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, file.getSize()));

            log.info("Файл '{}' загружен успешно в бакет '{}'", fileName, BUCKET_NAME);
        } catch (IOException e) {
            log.error("Ошибка при чтении файла: {}", e.getMessage(), e);

            throw new RuntimeException("Не удалось прочитать файл", e);
        }
    }
}
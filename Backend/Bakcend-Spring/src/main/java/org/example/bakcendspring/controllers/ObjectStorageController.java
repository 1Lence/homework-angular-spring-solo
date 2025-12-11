package org.example.bakcendspring.controllers;

import lombok.RequiredArgsConstructor;
import org.example.bakcendspring.services.ObjectStorageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/object")
@RequiredArgsConstructor
public class ObjectStorageController {
    private final ObjectStorageService objectStorageService;

    @PostMapping(path = "/{fileName}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void loadObject(@RequestPart("file") MultipartFile file,
                           @PathVariable String fileName) {
        objectStorageService.sendRequest(fileName, file);
    }
}
package com.colvir.config;

import com.amazonaws.services.s3.model.S3Object;

import java.util.Optional;

public interface S3Helper {

    void saveToS3(String bucket, byte[] fileBytes, String fileName, String contentType);

    Optional<S3Object> getS3Object(String bucket, String objectKey);

    boolean deleteObject(String bucket, String objectKey);
}

package com.backend.api.model.service;

import com.backend.api.helper.miscellaneous.PhotoLocation;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;

public interface Is3Service {

    PhotoLocation photoUpload(int id, MultipartHttpServletRequest multipartHttpServletRequest);

    public PhotoLocation s3Upload(int id, File file, String extension, String uniqueIdentifier);
}

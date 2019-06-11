package com.backend.api.model.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.backend.api.aws.s3.CommonConstants;
import com.backend.api.helper.miscellaneous.PhotoLocation;
import com.backend.api.helper.utility.FileUtility;
import com.backend.api.model.dao.IcustomerDao;
import com.backend.api.model.entity.Customer;
import com.backend.api.model.service.Is3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;


@Service
public class S3ServiceImpl implements Is3Service {

    @Autowired
    IcustomerDao icustomerDao;

    @Override
    public PhotoLocation photoUpload(int id, MultipartHttpServletRequest multipartHttpServletRequest) {

        MultipartFile multipartFile = multipartHttpServletRequest.getFile("photo");

        File file = FileUtility.convertMultiPartToFile(multipartFile);

        String filename = multipartFile.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf("."));

        if (multipartFile.getContentType().startsWith("image/")) {
            Customer customer = icustomerDao.getCustomerWithID(id);
            PhotoLocation photoLocation = s3Upload(id, file, extension, customer.getName());
            file.delete();
            return photoLocation;
        }

        return null;
    }


    @Override
    public PhotoLocation s3Upload(int id, File file, String extension, String uniqueIdentifier) {

        // credentials object identifying user for authentication
        AWSCredentials credentials = new BasicAWSCredentials(CommonConstants.ACCESS_KEY_ID, CommonConstants.ACCESS_SEC_KEY);


        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        String bucketName = CommonConstants.BUCKET_NAME;

        String folderName = CommonConstants.FOLDER_NAME;


        // upload file to folder and set it to public
        String fileName = folderName + CommonConstants.SUFFIX + uniqueIdentifier + extension;
        String url = "https://s3.us-east-2.amazonaws.com/backend-photos-assignment/" + fileName;

        s3client.putObject(new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
        return new PhotoLocation(url);
    }
}

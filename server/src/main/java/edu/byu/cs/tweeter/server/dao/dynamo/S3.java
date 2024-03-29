package edu.byu.cs.tweeter.server.dao.dynamo;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.nio.file.Paths;
import java.util.Base64;

public class S3 {
    public static String putImage(String imageName, String imageString) {
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withRegion("us-west-2")
                .build();

        //Bucket name
        String targetBucket = "jfran-tweeter-pictures";

        //File name/key
        String path = Paths.get(imageName + ".jpg").toString();

        //Convert image to a byte array
        byte[] imageByteArray = Base64.getDecoder().decode(imageString);

        //Set metadata
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageByteArray.length);
        metadata.setContentType("image/jpeg");

        //Create put request and upload image
        AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();

        PutObjectRequest req = new PutObjectRequest(targetBucket, path, new ByteArrayInputStream(imageByteArray), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
        client.putObject(req);

        //Get image url
        return s3.getUrl(targetBucket, path).toString();
    }
}



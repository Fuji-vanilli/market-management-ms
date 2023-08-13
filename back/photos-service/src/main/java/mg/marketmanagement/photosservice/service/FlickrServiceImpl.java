package mg.marketmanagement.photosservice.service;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.marketmanagement.photosservice.utils.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlickrServiceImpl implements FlickrService{
    private final Flickr flickr;

    @Override
    public Response savePhoto(InputStream photo, String title) throws FlickrException {
        UploadMetaData uploadMetaData= new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoId= flickr.getUploader()
                .upload(photo, uploadMetaData);

        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of(
                        "photo", flickr.getPhotosInterface()
                                .getPhoto(photoId)
                                .getMedium640Url()
                ))
                .message("photo save successfully!")
                .build();
    }
}

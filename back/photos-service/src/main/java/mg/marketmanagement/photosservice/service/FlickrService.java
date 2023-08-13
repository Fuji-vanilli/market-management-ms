package mg.marketmanagement.photosservice.service;

import com.flickr4java.flickr.FlickrException;
import mg.marketmanagement.photosservice.utils.Response;

import java.io.InputStream;

public interface FlickrService {
    Response savePhoto(InputStream photo, String title) throws FlickrException;
}

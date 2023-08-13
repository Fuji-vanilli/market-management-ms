package mg.marketmanagement.photosservice.controller;

import mg.marketmanagement.photosservice.utils.Response;
import org.springframework.http.ResponseEntity;

public interface PhotosController {
    ResponseEntity<Response> save();
}

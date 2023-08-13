package mg.marketmanagement.articleservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mg.marketmanagement.articleservice.dto.ArticleRequest;
import mg.marketmanagement.articleservice.utils.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static mg.marketmanagement.articleservice.utils.Root.APP_ROOT;

@Api(APP_ROOT+"/doc")
public interface ArticleController {
    @PostMapping("add")
    @ApiOperation(value = "save article",notes = "save or update an article", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "created successfully"),
            @ApiResponse(code = 400, message = "request not valid")
    })
    ResponseEntity<Response> add(@RequestBody ArticleRequest request) throws JSONException;
    @ApiOperation(value = "get article by code", notes = "code required!", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "article getted successfully"),
            @ApiResponse(code = 400, message = "code not valid")
    })
    @GetMapping("get/{codeArticle}")
    ResponseEntity<Response> getByCode(@PathVariable String codeArticle) throws JSONException;
    @GetMapping("all")
    @ApiOperation(value = "get all article", notes = "all article on the database", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "all article getted")
    })
    ResponseEntity<Response> getAll();
    @DeleteMapping("delete/{codeArticle}")
    @ApiOperation(value = "delete article by code", notes = "code required", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "article deleted successfully"),
            @ApiResponse(code = 400, message = "no article with the code")
    })
    ResponseEntity<Response> delete(@PathVariable String codeArticle);
}

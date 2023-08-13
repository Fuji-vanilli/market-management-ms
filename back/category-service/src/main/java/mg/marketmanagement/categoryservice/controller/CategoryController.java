package mg.marketmanagement.categoryservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mg.marketmanagement.categoryservice.dto.CategoryRequest;
import mg.marketmanagement.categoryservice.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static mg.marketmanagement.categoryservice.utils.Root.APP_ROOT;

@Api(APP_ROOT+"/doc")
public interface CategoryController {
    @PostMapping("add")
    @ApiOperation(value = "save category", notes = "add new category", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "new category add successfully"),
            @ApiResponse(code = 400, message = "bad request")
    })
    ResponseEntity<Response> add(@RequestBody CategoryRequest request);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}

package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDTO;
import guru.springfamework.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
//@RequestMapping(CategoryController.BASE_URL)
//public class CategoryController {
//    public static final String BASE_URL = "/api/v1/categories";
//    private final CategoryService categoryService;
//
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @GetMapping
//    public ResponseEntity<CategoryListDTO> getAllCategories() {
//        return new ResponseEntity<CategoryListDTO>(
//                new CategoryListDTO(categoryService.getAllCategories()),
//                HttpStatus.OK);
//    }
//
//    @GetMapping("{name}")
//    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String name) {
//        return new ResponseEntity<CategoryDTO>(
//                categoryService.getCategoryByName(name), HttpStatus.OK);
//    }
//}

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
    public static final String BASE_URL = "/api/v1/categories";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
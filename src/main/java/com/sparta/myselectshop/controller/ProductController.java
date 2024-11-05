package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.*;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.create(requestDto, userDetails.getUser());
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto createProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.update(id, requestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(@RequestParam("page") int page,
                                                @RequestParam("size") int size,
                                                @RequestParam("sortBy") String sortBy,
                                                @RequestParam("isAsc") boolean isAsc,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.getProducts(userDetails.getUser(), page - 1, size, sortBy, isAsc);
    }

    @GetMapping("/admin/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/product/{productId}/folder")
    public void addFolder(@PathVariable Long productId, @RequestParam Long folderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        productService.addFolder(productId, folderId, userDetails.getUser());
    }

    @GetMapping("/folders/{folderId}/products")
    public Page<ProductResponseDto> getProductsInFolder(@PathVariable Long folderId,
                                                        @RequestParam("page") int page,
                                                        @RequestParam("size") int size,
                                                        @RequestParam("sortBy") String sortBy,
                                                        @RequestParam("isAsc") boolean isAsc,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.getProductsInFolder(folderId, page - 1, size, sortBy, isAsc, userDetails.getUser());
    }
}

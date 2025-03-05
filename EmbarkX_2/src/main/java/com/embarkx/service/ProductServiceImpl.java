package com.embarkx.service;

import com.embarkx.exceptions.ApiException;
import com.embarkx.exceptions.ResourceNotFoundException;
import com.embarkx.model.Category;
import com.embarkx.model.Product;
import com.embarkx.payload.ProductDTO;
import com.embarkx.payload.ProductResponse;
import com.embarkx.repo.CategoryRepository;
import com.embarkx.repo.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    private String path;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetail = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage = productRepository.findAll(pageDetail);
        List<Product> products = productPage.getContent();

        if (products.isEmpty())
            throw new ApiException("No products created till now");

        return getProductResponse(productPage, products);
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Sort sort = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetail = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage = productRepository.findByCategoryOrderByPriceAsc(category, pageDetail);
        List<Product> products = productPage.getContent();

        if (products.isEmpty())
            throw new ApiException("No products in category " + category.getCategoryName() + " created till now");

        return getProductResponse(productPage, products);
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetail = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage = productRepository.findProductsByProductNameLikeIgnoreCase('%' + keyword + '%', pageDetail);
        List<Product> products = productPage.getContent();

        if (products.isEmpty())
            throw new ApiException("No products that contains '" + keyword + "' created till now");

        return getProductResponse(productPage, products);
    }


    private ProductResponse getProductResponse(Page<Product> productPage, List<Product> products) {
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        productResponse.setPageDetails(
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages() - 1,
                productPage.isLast()
        );

        return productResponse;
    }

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));

        Product product = modelMapper.map(productDTO, Product.class);

        Product productFromDb = productRepository.findProductsByProductNameLikeIgnoreCase(product.getProductName());
        boolean isProductPresented = productFromDb != null &&
                Objects.equals(productFromDb.getCategory().getCategoryId(), category.getCategoryId());

        if (isProductPresented)
            throw new ApiException("Product already exists!");


        double specialPrice = product.getPrice()
                - (product.getDiscount() * 0.01) * product.getPrice();
        product.setSpecialPrice(specialPrice);
        product.setCategory(category);
        product.setImage("default.png");
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product productToUpdate = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        Product product = modelMapper.map(productDTO, Product.class);

        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setQuantity(product.getQuantity());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDiscount(product.getDiscount());
        double specialPrice = productToUpdate.getPrice()
                - (productToUpdate.getDiscount() * 0.01) * productToUpdate.getPrice();
        productToUpdate.setSpecialPrice(specialPrice);

        Product updatedProduct = productRepository.save(productToUpdate);

        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product productToDelete = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        productRepository.delete(productToDelete);

        return modelMapper.map(productToDelete, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        Product productToUpdate = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        String fileName = fileService.uploadImage(path, image);
        productToUpdate.setImage(fileName);
        Product updatedProduct = productRepository.save(productToUpdate);

        return modelMapper.map(updatedProduct, ProductDTO.class);
    }
}

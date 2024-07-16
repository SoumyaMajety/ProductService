package com.scaler.fakestoreapi.service;

import com.scaler.fakestoreapi.dtos.FakeStoreRequestDto;
import com.scaler.fakestoreapi.dtos.FakeStoreResponseDto;
import com.scaler.fakestoreapi.models.Category;
import com.scaler.fakestoreapi.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakestoreProductService implements ProductServie {

    private RestTemplate restTemplate;

    private FakestoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(int id) {
        FakeStoreResponseDto responseDto = restTemplate
                .getForObject("https://fakestoreapi.com/products/" + id, FakeStoreResponseDto.class);
        if (responseDto == null)
            return null;

        return convertFakestoreResponseDtoTProduct(responseDto);
    }

    public Product[] getAllProducts() {

        FakeStoreResponseDto[] responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreResponseDto[].class);

        Product[] products = new Product[responseDto.length];
        for (int i = 0; i < responseDto.length; i++) {
            Product p = convertFakestoreResponseDtoTProduct(responseDto[i]);
            products[i] = p;
        }

        return products;
    }

    public Product convertFakestoreResponseDtoTProduct(FakeStoreResponseDto responseDto) {
        Product p = new Product();
        p.setId(responseDto.getId());
        p.setDescription(responseDto.getDescription());
        p.setImage(responseDto.getImage());
        p.setTitle(responseDto.getTitle());
        p.setPrice(responseDto.getPrice());
        Category c = new Category();
        c.setTitle(responseDto.getCategory());
        p.setCategory(c);
        return p;
    }

    @Override
    public Product updateProduct(Product product, int id) {
        FakeStoreRequestDto requestDto = convertProductToFakestoreRequestDto(product);
        FakeStoreResponseDto responseDto = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id,
                requestDto, FakeStoreResponseDto.class);
        Product p = convertFakestoreResponseDtoTProduct(responseDto);
        return p;
    }

    @Override
    public Product updateProductPut(Product product, int id) {
        FakeStoreRequestDto requestDto = convertProductToFakestoreRequestDto(product);
        restTemplate.put("https://fakestoreapi.com/products/" + id, requestDto);
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreRequestDto requestDto = convertProductToFakestoreRequestDto(product);
        FakeStoreResponseDto responseDto = restTemplate.postForObject("https://fakestoreapi.com/products/",
                requestDto, FakeStoreResponseDto.class);
        return convertFakestoreResponseDtoTProduct(responseDto);
    }

    public FakeStoreRequestDto convertProductToFakestoreRequestDto(Product p) {
        FakeStoreRequestDto requestDto = new FakeStoreRequestDto();
        requestDto.setCategory(p.getCategory().getTitle());
        requestDto.setTitle(p.getTitle());
        requestDto.setPrice(p.getPrice());
        requestDto.setImage(p.getImage());
        requestDto.setDescription(p.getDescription());

        return requestDto;

    }

    @Override
    public void deleteProduct(int id) {

        restTemplate.delete("https://fakestoreapi.com/products/" + id);

    }

    // for Pagenation

    public Page<Product> getAllProducts(int pageSize, int pageNumber){
        return null;
    }
}

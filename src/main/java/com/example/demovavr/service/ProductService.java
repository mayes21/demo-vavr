package com.example.demovavr.service;

import com.example.demovavr.model.LegacyProduct;
import com.example.demovavr.newmodel.Product;
import com.example.demovavr.repository.ProductRepository;
import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // legacy
    public Product oldSaveProduct(final LegacyProduct legacyProduct) {
        String productId = legacyProduct.getProductId();
        if (productId != null) {
            try {
                Long.valueOf(productId);
            } catch (NumberFormatException e) {
                log.error("Product ID [{}] is not a number", productId);
                return null;
            }
        } else {
            log.error("Product ID is null");
            return null;
        }

        List<Integer> colorList = legacyProduct.getColorList();
        if (colorList == null) {
            log.error("Color list is null");
            return null;
        }

        final Product product = toProduct(legacyProduct);
        return productRepository.save(product);
    }

    private static Product toProduct(final LegacyProduct legacyProduct) {
        return
                Product
                        .builder()
                        .id(Long.valueOf(legacyProduct.getProductId()))
                        .colorIds(legacyProduct.getColorList())
                        .build();
    }
}

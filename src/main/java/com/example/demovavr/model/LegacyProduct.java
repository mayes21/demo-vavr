package com.example.demovavr.model;

import io.vavr.collection.List;
import lombok.Builder;
import lombok.Value;


@Builder
@Value
public class LegacyProduct {

    String productId;
    List<Integer> colorList;
}
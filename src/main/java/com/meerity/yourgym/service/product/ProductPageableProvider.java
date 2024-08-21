package com.meerity.yourgym.service.product;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

@Component
public class ProductPageableProvider {

    Pageable getPageableWithSort(int pageNum, int size, String sortField, String sortDirection) {
        Sort sort = Sort.by(
                sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                sortField);
        return PageRequest.of(pageNum - 1, size, sort);
    }

}

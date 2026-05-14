package com.atrdev.ecomapp.modules.order.mapper;

import com.atrdev.ecomapp.modules.order.dto.OrderItemDTO;
import com.atrdev.ecomapp.modules.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "unitPrice", source = "price")
    OrderItemDTO toOrderItemDTO(OrderItem orderItem);
}

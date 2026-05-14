package com.atrdev.ecomapp.modules.order.mapper;

import com.atrdev.ecomapp.modules.order.dto.OrderResponse;
import com.atrdev.ecomapp.modules.order.entity.Order;
import com.atrdev.ecomapp.modules.product.mapper.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(target = "items", source = "orderItems")
    OrderResponse toOrderResponse(Order order);

}

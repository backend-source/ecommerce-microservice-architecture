package com.hoangtien2k3.orderservice.helper;

import com.hoangtien2k3.orderservice.domain.Cart;
import com.hoangtien2k3.orderservice.dto.CartDto;
import com.hoangtien2k3.orderservice.dto.UserDto;

public interface CartMappingHelper {

    static CartDto map(final Cart cart) {
        return CartDto.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .userDto(
                        UserDto.builder()
                                .userId(cart.getUserId())
                                .build())
                .build();
    }

    static Cart map(final CartDto cartDto) {
        return Cart.builder()
                .cartId(cartDto.getCartId())
                .userId(cartDto.getUserId())
                .build();
    }

}
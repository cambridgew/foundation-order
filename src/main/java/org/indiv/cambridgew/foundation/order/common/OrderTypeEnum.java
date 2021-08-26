package org.indiv.cambridgew.foundation.order.common;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 订单类型
 *
 * @author cambridge.w
 * @since 2021/8/23
 */
@Getter
public enum OrderTypeEnum {

    // 采购订单
    PURCHASE_ORDER("purchaseOrder", "采购订单"),

    // 销售商品订单
    COMMODITY_ORDER("commodityOrder", "销售商品订单"),

    ;

    @JsonValue
    @EnumValue
    private final String key;

    private final String description;

    OrderTypeEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }


}

package org.indiv.cambridgew.foundation.order.core;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 订单状态
 *
 * @author cambridge.w
 * @since 2021/8/20
 */
@Getter
public enum OrderState {

    UNPAID("unpaid", "待付款"),

    UNDELIVERED("undelivered", "待发货"),

    UNRECEIVED("unreceived", "待收货"),

    DEAL_COMPLETED("dealCompleted", "交易完成"),

    POST_SALE_SERVICE("postSaleService", "售后服务中"),

    DEAL_CLOSED("dealClosed", "交易关闭"),

    ;

    @JsonValue
    @EnumValue
    private String state;

    /**
     * 订单状态描述信息
     */
    private String description;

    OrderState(String state, String description) {
        this.state = state;
        this.description = description;
    }

}

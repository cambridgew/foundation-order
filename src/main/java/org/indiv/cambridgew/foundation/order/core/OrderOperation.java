package org.indiv.cambridgew.foundation.order.core;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 针对订单的操作
 *
 * @author cambridge.w
 * @since 2021/8/20
 */
@Getter
public enum OrderOperation {

    COMPLETE_PAYMENT("completePayment", "完成支付"),

    SUBMIT_LOGISTICS("submitLogistics", "提交物流信息"),

    RECEIVE_ITEM("receiveItem", "确认收货"),

    USER_CANCEL_ORDER("userCancelOrder", "用户取消订单"),

    SYS_CANCEL_ORDER("sysCancelOrder", "超时系统自动取消订单"),

    APPLY_FOR_REFUND("applyForRefund", "申请退款/退货"),

    ENSURE_RESULT_OF_REFUND_BOTH("ensureResultOfRefundBoth", "双方确认退款/退货结果"),

    ;

    @JsonValue
    @EnumValue
    private String operation;

    /**
     * 订单操作描述信息
     */
    private String description;

    OrderOperation(String operation, String description) {
        this.operation = operation;
        this.description = description;
    }

}

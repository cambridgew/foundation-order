package org.indiv.cambridgew.foundation.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.indiv.cambridgew.foundation.order.common.OrderTypeEnum;
import org.indiv.cambridgew.foundation.order.core.OrderState;
import org.indiv.cambridgew.poseidon.core.entity.BaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单实体(针对C端用户的订单信息)
 *
 * @author cambridge.w
 * @since 2021/8/23
 */
@Data
@TableName(value = "tb_order")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Order extends BaseEntity {

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 订单类型
     */
    private OrderTypeEnum orderType;

    /**
     * 订单状态
     */
    private OrderState orderState;

    /**
     * 订单渠道
     */
    private String orderChannel;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 收货地址
     */
    private String receiveAddress;

    /**
     * 商品Id
     */
    private Long itemId;

    /**
     * 商品单价
     */
    private Double itemUnitPrice;

    /**
     * 商品金额
     */
    private Double price;

    /**
     * 实际付款金额
     */
    private Double actualPaidPrice;

    /**
     * 运费
     */
    private Double transportationExpense;

    /**
     * 物流单号
     */
    private Long logisticsNumber;

    /**
     * 支付流水号
     */
    private String paymentSerialNumber;


    public void s (){
        int[] a= new int[2];
        for (int i:
             a) {
Map<Integer, Integer> map = new HashMap<>();
map.put(i, 1);
        }
    }
}

package org.indiv.cambridgew.foundation.order.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 订单状态机
 *
 * @author cambridge.w
 * @since 2021/8/20
 */
@Configuration
@EnableStateMachineFactory
public class OrderStateMachine extends EnumStateMachineConfigurerAdapter<OrderState, OrderOperation> {

    /**
     * 状态机状态定义
     *
     * @param state 状态机状态
     * @throws Exception 异常信息
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderOperation> state) throws Exception {
        state.withStates()
                .initial(OrderState.UNPAID)
                .states(EnumSet.allOf(OrderState.class));
    }

    /**
     * 状态机流转定义
     *
     * @param transitionConfigurer 状态机配置
     * @throws Exception 异常信息
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderOperation> transitionConfigurer) throws Exception {
        transitionConfigurer
                .withExternal()
                .source(OrderState.UNPAID)
                // 买家完成付款
                .event(OrderOperation.COMPLETE_PAYMENT)
                .target(OrderState.UNDELIVERED)

                .and()
                .withExternal()
                .source(OrderState.UNDELIVERED)
                // 卖家提交物流信息
                .event(OrderOperation.SUBMIT_LOGISTICS)
                .target(OrderState.UNRECEIVED)

                .and()
                .withExternal()
                .source(OrderState.UNRECEIVED)
                // 买家确认收货
                .event(OrderOperation.RECEIVE_ITEM)
                .target(OrderState.DEAL_COMPLETED)

                .and()
                .withExternal()
                .source(OrderState.POST_SALE_SERVICE)
                // 买卖双方确认售后结果
                .event(OrderOperation.ENSURE_RESULT_OF_REFUND_BOTH)
                .target(OrderState.DEAL_CLOSED)

                .and()
                .withExternal()
                .source(OrderState.UNRECEIVED)
                // 买家申请退款/退货
                .event(OrderOperation.APPLY_FOR_REFUND)
                .target(OrderState.POST_SALE_SERVICE)

                .and()
                .withExternal()
                .source(OrderState.UNDELIVERED)
                // 买家申请退款/退货
                .event(OrderOperation.APPLY_FOR_REFUND)
                .target(OrderState.POST_SALE_SERVICE)

                .and()
                .withExternal()
                .source(OrderState.UNPAID)
                // 买家取消订单
                .event(OrderOperation.USER_CANCEL_ORDER)
                .target(OrderState.DEAL_CLOSED)

                .and()
                .withExternal()
                .source(OrderState.UNPAID)
                // 超时系统自动取消订单
                .event(OrderOperation.SYS_CANCEL_ORDER)
                .target(OrderState.DEAL_CLOSED);
    }

    /**
     * 状态机配置定义
     *
     * @param config 状态机配置
     * @throws Exception 异常信息
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderOperation> config) throws Exception {
        config.withConfiguration().machineId("orderStateMachine");
    }

}

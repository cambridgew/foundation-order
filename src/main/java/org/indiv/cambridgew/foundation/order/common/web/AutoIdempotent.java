package org.indiv.cambridgew.foundation.order.common.web;

import java.lang.annotation.*;

/**
 * 幂等注解
 * @author cambridge.w
 * @since 2021/8/26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoIdempotent {
}

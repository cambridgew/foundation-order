package org.indiv.cambridgew.foundation.order.manager.common;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author cambridge.w
 * @since 2021/8/26
 */
@Component
public final class TokenManager {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 创建token
     *
     * @return 创建成功则返回token值, 失败则返回null
     */
    @Nullable
    public String createToken() {
        String tokenUUID = UUID.randomUUID().toString();

    }

    /**
     * 断言token有效(失败直接抛出异常)
     *
     * @param request http请求
     * @throws IllegalStateException 非法状态异常
     */
    public void assertToken(HttpServletRequest request) throws IllegalStateException {

    }
}

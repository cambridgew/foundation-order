package org.indiv.cambridgew.foundation.order.manager.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.indiv.cambridgew.foundation.order.constant.CoreConstants.REQUEST_TOKEN_NAME;
import static org.indiv.cambridgew.foundation.order.constant.CoreConstants.TOKEN_PREFIX;
import static org.indiv.cambridgew.foundation.order.constant.ErrorMsgConstants.TOKEN_INVALID;
import static org.indiv.cambridgew.foundation.order.constant.ErrorMsgConstants.TOKEN_NOT_EXIST;
import static org.springframework.util.Assert.isTrue;

/**
 * @author cambridge.w
 * @since 2021/8/26
 */
@Component
@Slf4j
public final class TokenManager {

    private static final Long EXPIRE_TIME = 10 * 60L;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 创建token
     *
     * @return 创建成功则返回token值, 失败则返回null
     */
    @Nullable
    public synchronized String createToken() {
        final String tokenUUID = TOKEN_PREFIX + UUID.randomUUID().toString();
        try {
            redisTemplate.opsForValue().set(tokenUUID, tokenUUID, EXPIRE_TIME, TimeUnit.SECONDS);
        } catch (Exception ex) {
            log.error("Error Creating Token, Error Message - {}", ex.getMessage());
            return null;
        }
        return tokenUUID;
    }

    /**
     * 断言token有效(失败直接抛出异常)
     *
     * @param request http请求
     * @throws IllegalStateException 非法状态异常
     */
    public void assertTokenAffective(HttpServletRequest request) throws IllegalStateException {
        final String token = Optional.ofNullable(request.getHeader(REQUEST_TOKEN_NAME))
                .orElse(request.getParameter(REQUEST_TOKEN_NAME));
        isTrue(!StringUtils.isEmpty(token), TOKEN_NOT_EXIST);
        if (!Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
            throw new IllegalStateException(TOKEN_INVALID);
        }
        if (!Boolean.TRUE.equals(redisTemplate.delete(token))) {
            throw new IllegalStateException(TOKEN_INVALID);
        }
    }

}

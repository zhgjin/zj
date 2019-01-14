package cn.sky999.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Authority {
    // 默认验证登录
    AuthorityType value() default AuthorityType.NoAuthority;
}


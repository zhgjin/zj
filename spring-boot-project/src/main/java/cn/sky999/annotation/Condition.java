package cn.sky999.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {
    public String value() default ConditionType.EQ;

    public String column() default "";
}

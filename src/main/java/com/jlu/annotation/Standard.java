package com.jlu.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/5/28.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Standard {
    String keyWord();

    String dataRegex() default "\\d+";


}

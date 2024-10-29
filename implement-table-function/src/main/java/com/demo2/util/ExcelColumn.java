package com.demo2.util;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  ExcelColumn {


        /**
         * Excel标题
         *
         * @return
         * @author Rex
         */
        String value() default "";

        /**
         * Excel从左往右排列位置，第一个是0
         *
         * @return
         * @author Rex
         */
        int col() default 0;
}


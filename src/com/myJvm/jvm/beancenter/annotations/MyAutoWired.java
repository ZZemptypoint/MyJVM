package com.myJvm.jvm.beancenter.annotations;

import java.lang.annotation.*;

/**
 * @author 22454
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyAutoWired {
}

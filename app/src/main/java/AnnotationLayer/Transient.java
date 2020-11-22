package AnnotationLayer;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Konumsal PC11 on 2.2.2016.
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Transient {
}

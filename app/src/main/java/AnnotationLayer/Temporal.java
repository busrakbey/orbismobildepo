package AnnotationLayer;

/**
 * Created by Ömer YILDIRIM on 2.2.2016.
 */
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import EnumsLayer.TemporalType;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Temporal {
    /**
     * The type used in mapping <code>java.util.Date</code> or <code>java.util.Calendar</code>.
     */
    TemporalType value();
}


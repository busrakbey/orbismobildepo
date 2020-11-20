package AnnotationLayer;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import EnumsLayer.CascadeType;
import EnumsLayer.FetchType;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Ömer YILDIRIM Konumsal PC11 on 30.12.2015.
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface OneToMany {

    /**
     * (Optional) The entity class that is the target
     * of the association. Optional only if the collection
     * property is defined using Java generics.
     * Must be specified otherwise.
     *
     * <p> Defaults to the parameterized type of
     * the collection when defined using generics.
     */
    Class targetEntity() default void.class;

    /**
     * (Optional) The operations that must be cascaded to
     * the target of the association.
     * <p> Defaults to no operations being cascaded.
     *
     * <p> When the target collection is a {@link java.util.Map
     * java.util.Map}, the <code>cascade</code> element applies to the
     * map value.
     */
    CascadeType[] cascade() default {};

    /** (Optional) Whether the association should be lazily loaded or
     * must be eagerly fetched. The EAGER strategy is a requirement on
     * the persistence provider runtime that the associated entities
     * must be eagerly fetched.  The LAZY strategy is a hint to the
     * persistence provider runtime.
     */
    FetchType fetch() default FetchType.LAZY;

    /**
     * The field that owns the relationship. Required unless
     * the relationship is unidirectional.
     */
    String mappedBy() default "";

    /**
     * (Optional) Whether to apply the remove operation to entities that have
     * been removed from the relationship and to cascade the remove operation to
     * those entities.
     * @since Java Persistence 2.0
     */
    boolean orphanRemoval() default false;
}


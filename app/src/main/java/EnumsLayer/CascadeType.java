package EnumsLayer;

/**
 * Created by Konumsal PC11 on 30.12.2015.
 */
public enum CascadeType
{
    /** Cascade all operations */
    ALL,

    /** Cascade persist operation */
    PERSIST,

    /** Cascade merge operation */
    MERGE,

    /** Cascade remove operation */
    REMOVE,

    /** Cascade refresh operation */
    REFRESH,

    /**
     * Cascade detach operation
     *
     * @since Java Persistence 2.0
     *
     */
    DETACH
}

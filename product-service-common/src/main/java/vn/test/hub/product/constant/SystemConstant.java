package vn.test.hub.product.constant;

public class SystemConstant {
    private SystemConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String NOT_FOUND = "Not Found %s with id: %s.";
    public static final String ID_NOT_BLANK = "%s id can not be blank.";
    public static final String ID_INVALID = "%s id = %s is invalid.";

    public static final String ID_COLUMN_NAME = "id";
    public static final String CREATED_AT_COLUMN_NAME = "created_at";
    public static final String CREATED_BY_COLUMN_NAME = "created_by";
    public static final String DELETED_COLUMN_NAME = "deleted";
}

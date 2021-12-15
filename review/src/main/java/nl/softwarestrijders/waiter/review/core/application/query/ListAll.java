package nl.softwarestrijders.waiter.review.core.application.query;

import java.util.List;

public record ListAll(String direction, String sort) {
    public static final String DIRECTION_ASCENDING = "asc";
    public static final String DIRECTION_DESCENDING = "desc";
    public static final String SORT_TITLE = "title";
    public static final String SORT_DESCRIPTION = "description";
    public static final String SORT_TYPE = "type";
    public static final String SORT_RATING = "rating";

    private static final List<String> DIRECTION_CRITERION = List.of(DIRECTION_ASCENDING, DIRECTION_DESCENDING);
    private static final List<String> SORT_CRITERION = List.of(SORT_TITLE, SORT_DESCRIPTION, SORT_TYPE, SORT_RATING);

    public ListAll {
        if (!DIRECTION_CRITERION.contains(direction)) {
            throw new IllegalArgumentException(String.format("Direction %s is an invalid direction value", direction));
        }

        if (!SORT_CRITERION.contains(sort)) {
            throw new IllegalArgumentException(String.format("Sort %s is an invalid sort value", sort));
        }
    }
}

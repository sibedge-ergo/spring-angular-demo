package com.sibedge.ergo.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

/**
 * Converts wildcards {@literal *, ?} to SQL LIKE compatible masks such as {@literal % and _}
 * respectively. It respects escapes in cases when LIKE meta-characters occur,
 */
@UtilityClass
public class WildcardConverter {

    public static final String[] STANDARD_SIMPLE_WILDCARD = new String[] {
        "*", "?", "%", "_"
    };
    public static final String[] STANDARD_SQL_LIKE_WILDCARD = new String[] {
        "%", "_", "\\%", "\\_"
    };

    /**
     * Converts {@literal *?} based patterns to SQL LIKE {@literal %_}.
     *
     * @param text original text
     * @return LIKE-compatible patter text
     */
    public String asSqlLikeWildcard(String text) {
        return StringUtils.replaceEach(text, STANDARD_SIMPLE_WILDCARD, STANDARD_SQL_LIKE_WILDCARD);
    }

}

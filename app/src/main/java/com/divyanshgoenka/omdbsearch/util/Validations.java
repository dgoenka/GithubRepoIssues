package com.divyanshgoenka.omdbsearch.util;

import java.util.Collection;

/**
 * Created by divyanshgoenka on 05/05/17.
 */

public class Validations {
    public static boolean isEmptyOrNull(Collection collection) {
        return collection == null || collection.size() < 1;
    }
}

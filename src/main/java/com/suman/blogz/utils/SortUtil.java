package com.suman.blogz.utils;

import org.springframework.data.domain.Sort;

public class SortUtil {
    public static Sort sortItems(String sortBy, String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc"))
            sort = Sort.by(sortBy).ascending();
        else
            sort = Sort.by(sortBy).descending();

        return sort;
    }
}

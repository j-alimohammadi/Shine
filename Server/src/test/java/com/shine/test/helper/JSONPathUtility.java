package com.shine.test.helper;

import com.jayway.jsonpath.JsonPath;

import java.io.UnsupportedEncodingException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class JSONPathUtility {
    public static <T> T read(String json, String path, Class<T> type) throws UnsupportedEncodingException {
        return JsonPath.parse(json)
                .read(path, type);
    }


}

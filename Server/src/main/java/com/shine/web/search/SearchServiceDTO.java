package com.shine.web.search;

import com.shine.core.search.domain.SearchCriteria;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andre Azzolini (apazzolini)
 */

public interface SearchServiceDTO {

    SearchCriteria buildSearchCriteria(HttpServletRequest httpServletRequest);

}

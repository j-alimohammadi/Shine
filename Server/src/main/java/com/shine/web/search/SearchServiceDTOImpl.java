package com.shine.web.search;

import com.shine.common.config.ShineConfigReader;
import com.shine.core.search.domain.SearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineSearchServiceDTOImpl")
public class SearchServiceDTOImpl implements SearchServiceDTO {

    @Override
    public SearchCriteria buildSearchCriteria(HttpServletRequest httpServletRequest) {

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPageSize(ShineConfigReader.readIntProperty("search.defaultPageSize"));

        Map<String, String[]> facets = new HashMap<>();

        for (Map.Entry<String, String[]> parameter : httpServletRequest.getParameterMap().entrySet()) {
            final String parameterName = parameter.getKey();

            if (Objects.equals(parameterName, SearchCriteria.PAGE_SIZE_PARAMETER)) {
                int pageSize = Integer.parseInt(parameter.getValue()[0]);
                int maxPageSize = ShineConfigReader.readIntProperty("search.maxPageSize");

                searchCriteria.setPageSize(Math.min(pageSize, maxPageSize));
            } else if (Objects.equals(parameterName, SearchCriteria.PAGE_NUMBER_PARAMETER)) {
                searchCriteria.setPage(Integer.valueOf(parameter.getValue()[0]));
            } else if (Objects.equals(parameterName, SearchCriteria.SORT_PARAMETER)) {
                Optional<String> sortByParameter = Stream.of(parameter.getValue())
                        .filter(StringUtils::isNotBlank)
                        .reduce((s, s2) -> {
                            return String.join(",", s, s2);
                        });

                searchCriteria.setSortBy(sortByParameter.orElse(""));
            } else if (Objects.equals(parameterName, SearchCriteria.QUERY_PARAMETER)) {
                searchCriteria.setQuery(parameter.getValue()[0]);

            } else {
                facets.put(parameter.getKey(), parameter.getValue());
            }
        }

        searchCriteria.setFilterCriteria(facets);

        return searchCriteria;
    }
}

package com.seekster.indexer.api.services;

import com.seekster.indexer.indexer.SearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    private final SearchQuery searchQuery;

    @Autowired
    public SearchService(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }

    public Map<String, List<Integer>> search(String query) {
        searchQuery.setQuery(query);
        System.out.println(searchQuery.search());
        return searchQuery.search();
    }
}

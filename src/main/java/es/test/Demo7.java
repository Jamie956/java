package es.test;

import es.utils.ESClient;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;

public class Demo7 {
    RestHighLevelClient client = ESClient.getClient();

    @Test
    public void scrollQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        builder.size(2);
        builder.sort("age", SortOrder.DESC);

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.scroll(TimeValue.timeValueMinutes(2L));
        request.source(builder);

        //search
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        String scrollId = response.getScrollId();
        System.out.println("===首页===");
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }

        while (true) {
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            searchScrollRequest.scroll(TimeValue.timeValueMinutes(1L));

            //scroll
            SearchResponse searchScrollResponse = client.scroll(searchScrollRequest, RequestOptions.DEFAULT);

            SearchHit[] hits = searchScrollResponse.getHits().getHits();
            if (hits != null && hits.length > 0) {
                System.out.println("===下一页===");
                for (SearchHit hit : hits) {
                    System.out.println(hit.getSourceAsMap());
                }
            } else {
                System.out.println("===结束===");
                break;
            }
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);

        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);

        System.out.println("删除scroll: " + clearScrollResponse.isSucceeded());

    }

}

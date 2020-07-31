package es.test;

import es.utils.ESClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

public class Demo9 {
    RestHighLevelClient client = ESClient.getClient();

    @Test
    public void boostingQuery() throws IOException {
        BoostingQueryBuilder boostingQuery = QueryBuilders.boostingQuery(
                QueryBuilders.matchQuery("interests", "喝"),
                QueryBuilders.matchQuery("name", "zhaoming")
        ).negativeBoost(0.5f);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(boostingQuery);

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }


    @Test
    public void boolQuery() throws IOException {

        /*
        # 名字 zhangsan或 lisi 或wangwu
        # 年龄不是26
        # 爱好包含唱，吃
         */
        BoolQueryBuilder boolQuery =QueryBuilders.boolQuery();
        boolQuery.should(QueryBuilders.termQuery("name", "zhangsan"));
        boolQuery.should(QueryBuilders.termQuery("name", "lisi"));
        boolQuery.should(QueryBuilders.termQuery("name", "wangwu"));
        boolQuery.mustNot(QueryBuilders.termQuery("age", 26));
        boolQuery.must(QueryBuilders.matchQuery("interests", "唱"));
        boolQuery.must(QueryBuilders.matchQuery("interests", "吃"));

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(boolQuery);

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}

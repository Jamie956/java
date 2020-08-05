package es.test;

import es.utils.ESClient;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;

import java.io.IOException;

public class Demo11 {
    RestHighLevelClient client = ESClient.getClient();

    @Test
    public void aggExtends() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.extendedStats("myagg").field("age"));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        ExtendedStats agg = response.getAggregations().get("myagg");
        System.out.println(agg.getMax());
    }

    @Test
    public void aggRange() throws IOException {
        //统计	x<21岁、x<=21 x<28岁、x>28岁
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.range("myagg").field("age").addUnboundedTo(21).addRange(21, 28).addUnboundedFrom(28));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        Range agg = response.getAggregations().get("myagg");
        for (Range.Bucket bucket : agg.getBuckets()) {
            Object  from = bucket.getFrom();
            Object to = bucket.getTo();
            String key = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            System.out.println(String.format("key=%s, docCount=%s, from=%s to=%s", key, docCount, from, to));
        }
    }

    @Test
    public void highlight() throws IOException {

        HighlightBuilder hBuilder = new HighlightBuilder();
        hBuilder.field("interests", 2).preTags("<font color='red'>").postTags("</font>");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("interests", "喝"));
        builder.highlighter(hBuilder);

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getHighlightFields().get("interests"));

        }
    }

    @Test
    public void aggCardinality() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.cardinality("group_birthday").field("birthday"));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Cardinality birthdayAgg = response.getAggregations().get("group_birthday");
        System.out.println(birthdayAgg.getValue());
    }
}

package es.test;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.entity.Person;
import es.utils.ESClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class TestMain {
    private RestHighLevelClient client = ESClient.getClient();
    private static final String INDEX = "person";
    private static final String TYPE = "man";

    /**
     * 创建索引
     */
    @Test
    public void createIndex() throws IOException {
        Settings.Builder settings = Settings.builder().put("number_of_shards", 3).put("number_of_replicas", 1);

        XContentBuilder mapping = JsonXContent.contentBuilder().startObject().startObject("properties").startObject("name").field("type", "text").endObject().startObject("age").field("type", "integer").endObject().startObject("birthday").field("type", "date").field("format", "yyyy-MM-dd").endObject().endObject().endObject();

        CreateIndexRequest request = new CreateIndexRequest(INDEX).settings(settings).mapping(TYPE, mapping);

        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    /**
     * 索引是否存在
     */
    @Test
    public void existIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(INDEX);

        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

        System.out.println(exists);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest();
        request.indices(INDEX);

        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println(response.isAcknowledged());
    }

    /**
     * 批量创建文档
     * GET /person/man/_search
     */
    @Test
    public void bulkCreateDoc() throws IOException {
        Person p1 = new Person(1, "Alis", 20, new Date());
        Person p2 = new Person(2, "Bobs", 20, new Date());
        Person p3 = new Person(3, "Cate", 20, new Date());

        ObjectMapper mapper = new ObjectMapper();
        String jons1 = mapper.writeValueAsString(p1);
        String jons2 = mapper.writeValueAsString(p2);
        String jons3 = mapper.writeValueAsString(p3);

        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest(INDEX, TYPE, p1.getId().toString()).source(jons1, XContentType.JSON));
        request.add(new IndexRequest(INDEX, TYPE, p2.getId().toString()).source(jons2, XContentType.JSON));
        request.add(new IndexRequest(INDEX, TYPE, p3.getId().toString()).source(jons3, XContentType.JSON));
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
    }

    /**
     * 批量删除文档
     */
    @Test
    public void bulkDeleteDoc() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest(INDEX, TYPE, "1"));
        request.add(new DeleteRequest(INDEX, TYPE, "2"));
        request.add(new DeleteRequest(INDEX, TYPE, "3"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    /**
     * 创建索引和文档
     */
    @Test
    public void createIndexDoc() throws IOException {
        Person person = new Person(2, "jamie", 27, new Date());
        IndexRequest request = new IndexRequest(INDEX, TYPE, person.getId().toString()).source(JSONObject.toJSONString(person), XContentType.JSON);
        IndexResponse reponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(reponse.getResult().toString());
    }

    /**
     * 更新文档
     */
    @Test
    public void updateDoc() throws IOException {
        HashMap map = new HashMap();
        map.put("name", "Jamie Zhou");
        String docID = "1";

        UpdateRequest request = new UpdateRequest(INDEX, TYPE, docID).doc(map);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult().toString());
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest(INDEX, TYPE, "1");

        DeleteResponse respond = client.delete(request, RequestOptions.DEFAULT);

        System.out.println(respond.getResult().toString());
    }

    @Test
    public void termsQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0);
        builder.size(5);
        builder.query(QueryBuilders.termsQuery("birthday", "1970-12-12", "1998-10-12"));

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
    public void multiMatchQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.multiMatchQuery("步", "interests", "address"));

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
    public void booleanMatchQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("interests", "喝 跑").operator(Operator.AND));

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
    public void matchQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("interests", "喝酒睡觉"));

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
    public void matchAllQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());

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
    public void findByRegexp() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.regexpQuery("name", "zha[a-z]*"));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()){
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void findByRange() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.rangeQuery("age").lte(28).gte(22));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()){
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void findByFuzzy() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("name", "zhangxan").prefixLength(5));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()){
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void findByPrefix() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.prefixQuery("interests", "喝"));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()){
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void findByIds() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.idsQuery().addIds("1", "2"));

        SearchRequest request = new SearchRequest();
        request.indices("lib");
        request.types("user");
        request.source(builder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()){
            System.out.println(hit.getSourceAsMap());
        }
    }

    @Test
    public void findById() throws IOException {
        GetRequest request = new GetRequest("lib", "user", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsMap());
    }


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


    @Test
    public void deleteByQuery() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest();
        request.indices("lib");
        request.types("user");
        request.setQuery(QueryBuilders.rangeQuery("age").lt(24));

        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
    }


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


    /**
     * 1.
     */
    @Test
    public void filter() throws IOException {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.filter(QueryBuilders.termQuery("name", "zhangsan"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("age").lte(30));

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(boolQueryBuilder);

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

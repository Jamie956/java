package es.test;

import es.utils.ESClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.junit.Test;

import java.io.IOException;

public class Demo8 {
    RestHighLevelClient client = ESClient.getClient();

    @Test
    public void deleteByQuery() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest();
        request.indices("lib");
        request.types("user");
        request.setQuery(QueryBuilders.rangeQuery("age").lt(24));

        BulkByScrollResponse response = client.deleteByQuery(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
    }
}

package es.utils;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClient {
    public static RestHighLevelClient getClient(){
        HttpHost http = new HttpHost("localhost", 9200);
        RestClientBuilder builder = RestClient.builder(http);
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }
}

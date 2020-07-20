package es.test;

import es.utils.ESClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;

public class Demo1 {

    @Test
    public void connectES(){
        RestHighLevelClient client  = ESClient.getClient();
        System.out.println("OK");
    }
}

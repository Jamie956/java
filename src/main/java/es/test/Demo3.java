package es.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.entity.Person;
import es.utils.ESClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;


import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class Demo3 {
    ObjectMapper mapper = new ObjectMapper();

    RestHighLevelClient client = ESClient.getClient();
    String index = "person";
    String type = "man";


    @Test
    public void createIndex() throws IOException {

        Person person = new Person(1, "jamie", 27, new Date());
        mapper.writeValueAsString(person);

        IndexRequest request = new IndexRequest(index, type, person.getId().toString());
        request.source(person, XContentType.JSON);


        IndexResponse reponse = client.index(request, RequestOptions.DEFAULT);

        System.out.println(reponse.getResult().toString());


    }
}

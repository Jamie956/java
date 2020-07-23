package es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.entity.Person;
import es.utils.ESClient;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;


import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class Demo3 {
    ObjectMapper mapper = new ObjectMapper();

    RestHighLevelClient client = ESClient.getClient();
    String index = "person";
    String type = "man";


    @Test
    public void bulkDelete() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest(index, type,"1"));
        request.add(new DeleteRequest(index, type,"2"));
        request.add(new DeleteRequest(index, type,"3"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    @Test
    public void bulkCreate() throws IOException {
        Person p1 = new Person(1, "Alis", 20, new Date());
        Person p2 = new Person(2, "Bobs", 20, new Date());
        Person p3 = new Person(3, "Cate", 20, new Date());

        String jons1 = mapper.writeValueAsString(p1);
        String jons2 = mapper.writeValueAsString(p2);
        String jons3 = mapper.writeValueAsString(p3);

        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest(index, type, p1.getId().toString()).source(jons1, XContentType.JSON));
        request.add(new IndexRequest(index, type, p2.getId().toString()).source(jons2, XContentType.JSON));
        request.add(new IndexRequest(index, type, p3.getId().toString()).source(jons3, XContentType.JSON));
        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
    }


    @Test
    public void deleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest(index, type, "1");

        DeleteResponse respond = client.delete(request, RequestOptions.DEFAULT);

        System.out.println(respond.getResult().toString());
    }

    @Test
    public void updateDoc() throws IOException {
        HashMap map = new HashMap();
        map.put("name", "Jamie Zhou");
        String docID = "1";

        UpdateRequest request = new UpdateRequest(index, type, docID).doc(map);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult().toString());

    }

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

import com.alibaba.fastjson.JSON;
import com.guo.springcloud.Search9200;
import com.guo.springcloud.config.ElasticSearchConfig;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Date: 2021/3/19 9:18
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */


class PrintScource {

    private int numer = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (numer != 1) {
                condition.await();
            }

            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }

            numer = 2;
            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }

    public void print10() throws InterruptedException {
        lock.lock();
        try {
            while (numer != 2) {
                condition.await();
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }

            numer = 1;
            condition.signalAll();

        } finally {
            lock.unlock();
        }
    }

}

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Search9200.class})
public class SearchTest {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void testThreadLearn() throws ExecutionException, InterruptedException {
        Collections.synchronizedList(new ArrayList<>());

        new CopyOnWriteArrayList<>();
        new CopyOnWriteArraySet<>();
        PrintScource printScource = new PrintScource();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    printScource.print5();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    printScource.print10();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();


        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });

        new Thread(task).start();
        task.get();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
                1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "银行业务");
                    }
                });
    }

    @Test
    public void testClient() {
        System.out.println(client);
    }

    /**
     * 测试保存数据到es
     * 添加+更新操作
     * @throws IOException
     */
    @Test
    public void indexData() throws IOException {

        List<String> list = new ArrayList<>();

        Map<String, String> map = new HashMap<>();

        LockSupport.park();

        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");// 数据id
        // 第一种方式
//        indexRequest.source("userName","zhangsan","age",18,"gender","男");

        // 第二种 推荐
        User user = new User();
        user.setUserName("张三");
        user.setAge(18);
        user.setGender("男");
        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON);// 要保存的内容

        // 真正的保存信息到es
//        IndexResponse indexResponse = client.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS); // 这里使用自定义的报错了
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        // 提取响应数据
        System.out.println(indexResponse);


    }

    /**
     * 查询所有
     */
    @Test
    public void searchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest("users");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(10);
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(search);
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

    }

    @Test
    public void searchBoolQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("newbank");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder
                .must(QueryBuilders.matchQuery("address", "mill"))
                .must(QueryBuilders.matchQuery("gender", "M"))
                .mustNot(QueryBuilders.termQuery("age", 18))
                .should(QueryBuilders.matchQuery("lastname", "Wallace"))
                .filter(QueryBuilders.rangeQuery("balance").lte(19648).gte(30000));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse);


    }

    /**
     * 聚合查询
     */
    @Test
    public void aggsQuery() throws IOException {
        TermsAggregationBuilder termsAggregationBuilder =
                AggregationBuilders.terms("ageAgg")
                        .field("age").
                        subAggregation(
                                AggregationBuilders
                                .avg("blanceAvg")
                                .field("balance"));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.aggregation(termsAggregationBuilder);

        SearchRequest searchRequest = new SearchRequest("newbank");
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse);


    }

    class User {
        private String userName;
        private String gender;
        private Integer age;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}

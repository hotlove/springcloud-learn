package com.guo.springcloud.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/3/18 22:47
 * @Description:
 */
@Configuration
public class ElasticSearchConfig {

    /**
     * 配置一个请求配置项做成单例得共享使用
     */
    private static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        builder.addHeader("Authorization", "Bearer " + "TOKEN");
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    /**
     * 获取一个es 9200端口访问客户端
     * 文档地址：https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-getting-started-initialization.html
     * @return
     */
    @Bean
    public RestHighLevelClient getRestHighLevelClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("47.99.113.40", 9200, "http")));

        return client;
    }
}

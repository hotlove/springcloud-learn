import com.alibaba.fastjson.JSON;
import com.guo.springcloud.Search9200;
import com.guo.springcloud.config.ElasticSearchConfig;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.IOException;

/**
 * @Date: 2021/3/19 9:18
 * @Author 郭乐建
 * @Since JDK 1.8
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Search9200.class})
public class SearchTest {

    @Resource
    private RestHighLevelClient client;

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
        IndexResponse indexResponse = client.index(indexRequest, ElasticSearchConfig.COMMON_OPTIONS);

        // 提取响应数据
        System.out.println(indexResponse);


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

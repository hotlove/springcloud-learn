import com.guo.springcloud.Search9200;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
}

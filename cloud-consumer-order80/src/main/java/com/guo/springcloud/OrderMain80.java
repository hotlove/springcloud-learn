package com.guo.springcloud;

import com.guo.myrule.MySelfRule;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Auther: hotlove_linx
 * @Date: 2020/8/26 22:08
 * @Description:
 */
@SpringBootApplication
// 从Spring Cloud Edgware开始，@EnableEurekaClient 或 @EnableDiscoveryClient 是可省略的。只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMNET-SERVICE", configuration = MySelfRule.class)
public class OrderMain80 {
    private static Logger logger = LoggerFactory.getLogger(OrderMain80.class);

    public static void main(String[] args) {
        String localIp = getLocalIp();
        System.out.println("lcoalIp:"+localIp);
        SpringApplication.run(OrderMain80.class, args);
    }

    // 获取本机ip
    public static String getLocalIp() {
        String sysEnv = "prod";
        try {
            Enumeration<NetworkInterface> faces = NetworkInterface.getNetworkInterfaces();
            while (faces.hasMoreElements()) { // 遍历网络接口
                NetworkInterface face = faces.nextElement();
                if (face.isLoopback() || face.isVirtual() || !face.isUp()) {
                    continue;
                }

                if (sysEnv.equals("prod") || sysEnv.equals("test") || sysEnv.equals("dev")) {
                    // 线上环境
                    // 测试环境和线上环境过滤掉其他网卡数据
                    if (!face.getDisplayName().contains("eth")) {
                        continue;
                    }
                }

                Enumeration<InetAddress> address = face.getInetAddresses();
                if (address == null) {
                    continue;
                }
                while (address.hasMoreElements()) { // 遍历网络地址
                    InetAddress addr = address.nextElement();
                    if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress() && !addr.isAnyLocalAddress()) {

                        String localIp = addr.getHostAddress();
                        if (StringUtils.isBlank(localIp)) {
                            continue;
                        }

                        if (sysEnv.equals("prod")) {
                            // 线上环境
                            if (localIp.startsWith("172")) {
                                logger.info("网络接口名：" + face.getDisplayName() + "，地址："+localIp);
                                return localIp;
                            }
                        }

                        if (sysEnv.equals("test")) {
                            // 测试环境
                            if (localIp.startsWith("112")) {
                                logger.info("网络接口名：" + face.getDisplayName() + "，地址："+localIp);
                                return localIp;
                            }
                        }
                        logger.info("网络接口名：" + face.getDisplayName() + "，地址："+localIp);
                        return localIp;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}

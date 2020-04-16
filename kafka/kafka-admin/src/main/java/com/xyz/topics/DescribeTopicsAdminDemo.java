package com.xyz.topics;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class DescribeTopicsAdminDemo {

    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-admin";

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient adminClient = AdminClient.create(properties);


        DescribeTopicsResult result = adminClient.describeTopics(Collections.singletonList(topic));

        try {
            Map<String, TopicDescription> descriptionMap = result.all().get();
            System.out.println(descriptionMap);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        adminClient.close();

    }

}

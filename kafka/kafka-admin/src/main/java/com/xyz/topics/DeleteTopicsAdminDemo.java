package com.xyz.topics;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class DeleteTopicsAdminDemo {

    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-logs";

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);

        AdminClient adminClient = AdminClient.create(properties);
        DeleteTopicsResult result = adminClient.deleteTopics(Collections.singletonList(topic));
        try {
            result.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        adminClient.close();

    }

}

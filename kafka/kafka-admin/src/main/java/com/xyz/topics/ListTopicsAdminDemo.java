package com.xyz.topics;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class ListTopicsAdminDemo {

    public static final String brokerList = "localhost:9092";

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient adminClient = AdminClient.create(properties);


        ListTopicsResult result = adminClient.listTopics();

        try {
            Set<String> names = result.names().get();
            System.out.println(names);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        adminClient.close();

    }

}

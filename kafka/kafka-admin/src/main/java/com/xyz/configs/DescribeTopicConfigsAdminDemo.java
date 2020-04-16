package com.xyz.configs;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.AlterConfigsResult;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.DescribeConfigsOptions;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class DescribeTopicConfigsAdminDemo {

    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-admin";

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient adminClient = AdminClient.create(properties);

        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topic);

        DescribeConfigsResult result = adminClient.describeConfigs(Collections.singletonList(resource));

        try {
            Map<ConfigResource, Config> configMap = result.all().get();
            System.out.println(configMap);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        adminClient.close();

    }

}

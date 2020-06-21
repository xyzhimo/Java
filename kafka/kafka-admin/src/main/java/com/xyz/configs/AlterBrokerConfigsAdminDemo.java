package com.xyz.configs;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.AlterConfigsResult;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class AlterBrokerConfigsAdminDemo {

    public static final String brokerList = "localhost:9092";
    public static final String brokerId = "0";

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient adminClient = AdminClient.create(properties);

        ConfigResource resource = new ConfigResource(ConfigResource.Type.BROKER, brokerId);

//        ConfigEntry configEntry = new ConfigEntry(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT);
        ConfigEntry configEntry = new ConfigEntry("log.roll.ms", String.valueOf(10000));
        Config config = new Config(Collections.singletonList(configEntry));

        Map<ConfigResource, Config> configs = new HashMap<>();
        configs.put(resource, config);
        AlterConfigsResult result = adminClient.alterConfigs(configs);

        try {
            result.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        adminClient.close();

    }

}

package com.spmv.uni.config;

import io.sphere.sdk.client.BlockingSphereClient;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereClientConfig;
import io.sphere.sdk.client.SphereClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//@Configuration
class CommercetoolsPlatformConfig {

    private static final String PROPERTIES_FILE_NAME = "ctp.properties";

    @Bean(destroyMethod="close")
    public BlockingSphereClient sphereClient() throws IOException {
        final Properties properties = PropertiesLoaderUtils.loadAllProperties(PROPERTIES_FILE_NAME);
        final String prefix = "";
        final SphereClientConfig clientConfig = SphereClientConfig.ofProperties(properties, prefix);
        final SphereClient sphereClient = SphereClientFactory.of().createClient(clientConfig);
        return BlockingSphereClient.of(sphereClient, 15, TimeUnit.SECONDS);
    }
}

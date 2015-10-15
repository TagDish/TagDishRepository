package com.tagdish.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@PropertySource(value = "classpath:elasticsearch.properties")
@EnableElasticsearchRepositories(basePackages = "com.tagdish.dao.repository")
public class ElasticsearchConfiguration {
	
//    @Resource
//    private Environment environment;
    
	//To resolve ${} in @Value
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Value("${elasticsearch.host}")
	private String elasticSearchHost;
	
	@Value("${elasticsearch.port}")
	private int elasticSearchPort;	
    
    @Bean
    public Client client() {
        TransportClient client = new TransportClient();
        TransportAddress address = new InetSocketTransportAddress(elasticSearchHost, elasticSearchPort);
        client.addTransportAddress(address);        
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
     }
}
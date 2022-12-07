package rs.novacode.meetup.attendees.lottery.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestClientConfig {

    @Value("${rest.client.http.max.connections:50}")
    private int maxConnections;

    @Value("${rest.client.http.max.connections.per.route:5}")
    private int maxConnectionPerRoute;

    @Value("${rest.client.http.request.timeout:30000}")
    private int requestTimeout;

    @Value("${rest.client.http.connection.timeout:30000}")
    private int connectTimeout;

    @Value("${rest.client.http.socket.timeout:30000}")
    private int socketTimeout;

    @Bean
    public RestTemplate restTemplate() {
        RequestConfig requestConfig = RequestConfig.custom()
            .setResponseTimeout(5000, TimeUnit.MILLISECONDS)
            .setConnectTimeout(5000, TimeUnit.MILLISECONDS)
            .setConnectionRequestTimeout(5000, TimeUnit.MILLISECONDS)
            .build();
        CloseableHttpClient httpclient = HttpClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .build();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpclient);
        return new RestTemplate(httpRequestFactory);
    }


}

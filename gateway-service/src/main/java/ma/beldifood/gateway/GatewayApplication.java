package ma.beldifood.gateway;

<<<<<<< HEAD
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.netty.http.client.HttpClient;
=======
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication.class, args);
    }

<<<<<<< HEAD
    /*@Bean
    DiscoveryClientRouteDefinitionLocator locator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp ){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }*/
    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
=======
    @Bean
    DiscoveryClientRouteDefinitionLocator locator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp ){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
>>>>>>> 86da0c2e621f63cba7797a7f88ab1a46d30e9f9c
    }

}

package com.hasaker.spring_boot_demo.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.function.Function;

import static io.netty.channel.ChannelOption.TCP_NODELAY;


@Configuration
public class WebClientConfig {

    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplate httpsRestTemplate = new RestTemplate(generateHttpRequestFactory());

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>("{}", requestHeaders);

        WebClientConfig clientConfig = new WebClientConfig();
        WebClient httpClient = clientConfig.httpWebClient();
        WebClient httpsClient = clientConfig.httpsWebClient();
        String url = "http://collect.weidu.ac.cn/api/fscti/pushEvent";
        String url1 = "http://localhost:8192/api/channel/token?dn=123";
        String urls = "https://collect.weidu.ac.cn/api/fscti/pushEvent";
//        String a = httpsClient.post().uri(url).body(Mono.just("{}"), String.class).retrieve().bodyToMono(String.class).block();
//        String b = httpsClient.post().uri(urls).body(Mono.just("{}"), String.class).retrieve().bodyToMono(String.class).block();
//        String c = httpsClient.get().uri(url1).retrieve().bodyToMono(String.class).block();
//        String d = restTemplate.getForObject(url1, String.class);
        String f = httpsRestTemplate.getForObject(url1, String.class);
        ResponseEntity<String> response = httpsRestTemplate.exchange(urls, HttpMethod.POST, requestEntity, String.class);
        ResponseEntity<String> aaa = httpsRestTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println();
    }

    @Bean
    ReactorResourceFactory resourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        factory.setConnectionProvider(ConnectionProvider.create("httpClient", 200));
        factory.setLoopResources(LoopResources.create("httpClient", 50, true));
        return factory;
    }

    @Bean
    WebClient httpWebClient() {
        Function<HttpClient, HttpClient> mapper = client -> client
                .tcpConfiguration(c -> c
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                        .option(TCP_NODELAY, true)
                        .doOnConnected(connection -> {
                            connection.addHandlerLast(new ReadTimeoutHandler(10));
                            connection.addHandlerLast(new WriteTimeoutHandler(10));
                        }));

        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(resourceFactory(), mapper))
                .build();
    }

    @Bean
    WebClient httpsWebClient() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(tcp -> tcp
                        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                        .option(TCP_NODELAY, true)
                        .doOnConnected(connection -> {
                            connection.addHandlerLast(new ReadTimeoutHandler(10));
                            connection.addHandlerLast(new WriteTimeoutHandler(10));
                        })
                )
                .secure(t -> t.sslContext(sslContext));

        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    private static HttpComponentsClientHttpRequestFactory generateHttpRequestFactory() throws Exception{
        TrustStrategy acceptingTrustStrategy = ((x509Certificates, authType) -> true);
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
        return factory;
    }

    static class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {

        private final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };

        @Override
        protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
            try {
                if (connection instanceof HttpsURLConnection httpsConnection) {
                    SSLContext sslContext = SSLContext.getInstance("SSL");
                    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                    httpsConnection.setSSLSocketFactory(new MyCustomSSLSocketFactory(sslContext.getSocketFactory()));
                    httpsConnection.setHostnameVerifier((s, sslSession) -> true);
                    super.prepareConnection(httpsConnection, httpMethod);
                } else {
                    super.prepareConnection(connection, httpMethod);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static class MyCustomSSLSocketFactory extends SSLSocketFactory {

            private final SSLSocketFactory delegate;

            public MyCustomSSLSocketFactory(SSLSocketFactory delegate) {
                this.delegate = delegate;
            }

            // 返回默认启用的密码套件。除非一个列表启用，对SSL连接的握手会使用这些密码套件。
            // 这些默认的服务的最低质量要求保密保护和服务器身份验证
            @Override
            public String[] getDefaultCipherSuites() {
                return delegate.getDefaultCipherSuites();
            }

            // 返回的密码套件可用于SSL连接启用的名字
            @Override
            public String[] getSupportedCipherSuites() {
                return delegate.getSupportedCipherSuites();
            }


            @Override
            public Socket createSocket(final Socket socket, final String host, final int port,
                                       final boolean autoClose) throws IOException {
                final Socket underlyingSocket = delegate.createSocket(socket, host, port, autoClose);
                return overrideProtocol(underlyingSocket);
            }


            @Override
            public Socket createSocket(final String host, final int port) throws IOException {
                final Socket underlyingSocket = delegate.createSocket(host, port);
                return overrideProtocol(underlyingSocket);
            }

            @Override
            public Socket createSocket(final String host, final int port, final InetAddress localAddress,
                                       final int localPort) throws
                    IOException {
                final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
                return overrideProtocol(underlyingSocket);
            }

            @Override
            public Socket createSocket(final InetAddress host, final int port) throws IOException {
                final Socket underlyingSocket = delegate.createSocket(host, port);
                return overrideProtocol(underlyingSocket);
            }

            @Override
            public Socket createSocket(final InetAddress host, final int port, final InetAddress localAddress,
                                       final int localPort) throws
                    IOException {
                final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
                return overrideProtocol(underlyingSocket);
            }

            private Socket overrideProtocol(final Socket socket) {
                if (!(socket instanceof SSLSocket)) {
                    throw new RuntimeException("An instance of SSLSocket is expected");
                }
                ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1"});
                return socket;
            }
        }
    }
}

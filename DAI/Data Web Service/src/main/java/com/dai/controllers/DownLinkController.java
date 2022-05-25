package com.dai.controllers;

import com.dai.model.SocketProperties;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;

@Component
public class DownLinkController implements WebSocket.Listener{

    private SocketProperties properties;

    public DownLinkController(SocketProperties properties) {
        this.properties = properties;
    }

    public void sendDownLink(String jsonTelegram) {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(properties.getUrl()), this);
        WebSocket server = ws.join();
        System.out.println("Sending downLink telegram: " + jsonTelegram);
        server.sendText(jsonTelegram, true);
        server.abort();
    }
}

package com.dai.controllers;
import com.dai.service.socketMeasurement.SocketMeasurementService;
import com.dai.model.SocketData;
import com.dai.model.SocketProperties;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Profile("prod")
@Component
public class WebsocketClientController implements WebSocket.Listener {

    private SocketMeasurementService measurementModel;
    private WebSocket server = null;

    @Autowired
    public WebsocketClientController(SocketMeasurementService measurementModel, SocketProperties properties) {
        this.measurementModel = measurementModel;
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(properties.getUrl()), this);
        server = ws.join();
    }

    public void sendDownLink(String jsonTelegram) {
        server.sendText(jsonTelegram, true);
    }

    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
        System.out.println("WebSocket Listener has been opened for requests.");
    }

    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("A " + error.getCause() + " exception was thrown.");
        System.out.println("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }

    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("WebSocket closed!");
        System.out.println("Status:" + statusCode + " Reason: " + reason);
        return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Pong: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        String json = data.toString();
        Gson gson = new Gson();
        SocketData socketData = gson.fromJson(json, SocketData.class);
        System.out.println("Received data: " + socketData.toString());
        try {
            measurementModel.saveSocketData(socketData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        webSocket.request(1);
        return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
    }
}

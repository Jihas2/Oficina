package com.oficina.app.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExternalApiClient {
    private final HttpClient client = HttpClient.newHttpClient();

    public String buscarDadosVeiculo(String url) throws Exception {
        if (url == null) throw new IllegalArgumentException("URL não pode ser nula");
        if (url.startsWith("file:")) {
            String path = url.substring(5);
            return new String(Files.readAllBytes(Path.of(path)));
        }
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() == 200) return resp.body();
        else throw new RuntimeException("Falha ao consultar API: " + resp.statusCode());
    }
}

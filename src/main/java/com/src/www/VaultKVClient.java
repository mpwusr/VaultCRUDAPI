// VaultKVClient.java
package com.src.www;

import okhttp3.*;
import java.io.IOException;

public class VaultKVClient {

    private final String addr;
    private final String token;
    private final OkHttpClient http;

    public VaultKVClient(String addr, String token) {
        this.addr  = addr.endsWith("/") ? addr.substring(0, addr.length()-1) : addr;
        this.token = token;
        this.http  = new OkHttpClient();
    }

    /* ---------- low-level helpers ---------- */
    private String url(String endpoint) { return addr + "/v1/" + endpoint; }

    private Request.Builder reqBuilder(String endpoint) {
        Request.Builder builder = new Request.Builder()
                .url(url(endpoint))
                .addHeader("X-Vault-Token", token);

        return builder;
    }


    private Response call(Request req) throws IOException {
        Response res = http.newCall(req).execute();
        if (!res.isSuccessful())
            throw new IOException(res.code() + " → " + res.body().string());
        return res;
    }

    /* ---------- CRUDL ---------- */
    public String createOrUpdate(String path, String jsonData) throws IOException {
        String payload = "{ \"data\": " + jsonData + " }";
        Request req = reqBuilder("secret/data/" + path)
                .post(RequestBody.create(payload, MediaType.parse("application/json")))
                .build();
        return call(req).body().string();
    }

    public String read(String path) throws IOException {
        Request req = reqBuilder("secret/data/" + path).get().build();
        return call(req).body().string();
    }

    public String list(String prefix) throws IOException {
        // try LIST first
        Request listReq = reqBuilder("secret/metadata/" + prefix).method("LIST", null).build();
        try {
            return call(listReq).body().string();
        } catch (IOException e) {
            // fallback GET ?list=true
            HttpUrl url = HttpUrl.parse(url("secret/metadata/" + prefix))
                    .newBuilder().addQueryParameter("list", "true").build();
            Request getReq = reqBuilder("").url(url).get().build();
            return call(getReq).body().string();
        }
    }

    public String deleteSoft(String path) throws IOException {
        Request req = reqBuilder("secret/metadata/" + path).delete().build();
        return call(req).body().string();
    }

    public String deleteHard(String path) throws IOException {
        Request req = reqBuilder("secret/data/" + path).delete().build();
        return call(req).body().string();
    }

    /* ---------------- demo ---------------- */
    public static void main(String[] args) throws Exception {
        String addr  = System.getenv().getOrDefault("VAULT_ADDR", "http://127.0.0.1:8200");
        String token = System.getenv("VAULT_TOKEN");
        // ← add
        VaultKVClient client = new VaultKVClient(addr, token);

        client.createOrUpdate("demo", "{ \"username\": \"bob\", \"pwd\": \"p4ss\" }");
        System.out.println("READ → " + client.read("demo"));
        System.out.println("LIST → " + client.list(""));
        client.deleteSoft("demo");
    }
}

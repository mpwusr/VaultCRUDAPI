package com.src.www;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Vault client starting...");

        // ① pick up VAULT_ADDR / VAULT_TOKEN from env
        VaultKVClient client = new VaultKVClient(
                System.getenv().getOrDefault("VAULT_ADDR", "http://127.0.0.1:8200"),
                System.getenv("VAULT_TOKEN")
        );

        // ② run a quick CRUDL demo
        client.createOrUpdate("demo", "{ \"hello\": \"vault\" }");
        System.out.println("READ  → " + client.read("demo"));
        System.out.println("LIST  → " + client.list(""));
        client.deleteSoft("demo");
        System.out.println("Secret deleted.");
    }
}

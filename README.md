# Vault KV Secrets API Clients (Java)

This project provides simple, reusable client in **Java** for managing secrets using the **HashiCorp Vault KV-v2 secrets engine**. 
It supports basic CRUDL operations:
- Create or update secrets
- Read secrets
- List secrets under a prefix
- Delete secrets (soft or hard delete)

---

## Prerequisites

- Vault installed and running (local or remote)
- Vault token (e.g., dev root token)
- Vault KV engine enabled (default at `/secret`)
- Java 11+ or Python 3.8+

---

## Setup Instructions

### 1. Install and Run Vault

#### Download and unzip Vault manually if Homebrew is blocked:
https://developer.hashicorp.com/vault/install

# Start Vault in dev mode:
```
vault server -dev
You will see a Root Token: s.XXXX in the console. 
Copy it.
```
## 2. Set Environment Variables
```
export VAULT_ADDR=http://127.0.0.1:8200
export VAULT_TOKEN=s.XXXXXXX  # Use the actual root token
```
## 3. Java Client Usage
# Build & Run
# Perform clean of java jar files
```
michaelwilliams@Michaels-MBP VaultCRUDAPI % gradle clean                           

[Incubating] Problems report is available at: file:///Users/michaelwilliams/working/VaultCRUDAPI/VaultCRUDAPI/build/reports/problems/problems-report.html

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.14.2/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 318ms
1 actionable task: 1 executed
```
# Perform build of java jar files
```
michaelwilliams@Michaels-MBP VaultCRUDAPI % gradle build

[Incubating] Problems report is available at: file:///Users/michaelwilliams/working/VaultCRUDAPI/VaultCRUDAPI/build/reports/problems/problems-report.html

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.14.2/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 757ms
9 actionable tasks: 9 executed
```
# List jar files
```
michaelwilliams@Michaels-MBP VaultCRUDAPI % ls build/libs 
VaultCRUDAPI-all.jar    VaultCRUDAPI.jar
```
# Run client and API calls in all jar
```
michaelwilliams@Michaels-MBP VaultCRUDAPI % java -jar build/libs/VaultCRUDAPI-all.jar
Vault client starting...
READ  → {"request_id":"ea43b0c8-8a41-6feb-15cb-7d19b7f103cb","lease_id":"","renewable":false,"lease_duration":0,"data":{"data":{"hello":"vault"},"metadata":{"created_time":"2025-06-26T19:42:02.398926Z","custom_metadata":null,"deletion_time":"","destroyed":false,"version":1}},"wrap_info":null,"warnings":null,"auth":null,"mount_type":"kv"}

LIST  → {"request_id":"ab86e998-a2ca-c822-375b-5286f9bce9cd","lease_id":"","renewable":false,"lease_duration":0,"data":{"keys":["demo"]},"wrap_info":null,"warnings":null,"auth":null,"mount_type":"kv"}

Secret deleted.
```
# License
```
MIT License – use freely with credit.
HashiCorp Vault is © HashiCorp under BUSL-1.1 / MPL.
```

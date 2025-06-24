# 🔐 Vault KV Secrets API Clients (Python + Java)

This project provides simple, reusable clients in **Python** and **Java** for managing secrets using the **HashiCorp Vault KV-v2 secrets engine**. It supports basic CRUDL operations:

- ✅ Create or update secrets
- ✅ Read secrets
- ✅ List secrets under a prefix
- ✅ Delete secrets (soft or hard delete)

---

## 🚀 Prerequisites

- Vault installed and running (local or remote)
- Vault token (e.g., dev root token)
- Vault KV engine enabled (default at `/secret`)
- Java 11+ or Python 3.8+

---

## 🔧 Setup Instructions

### 1. 🧰 Install and Run Vault

```bash
# Download and unzip Vault manually if Homebrew is blocked:
https://developer.hashicorp.com/vault/install

# Start Vault in dev mode:
vault server -dev
You will see a Root Token: s.XXXX in the console. Copy it.

2. 🛠 Set Environment Variables
bash
Copy
Edit
export VAULT_ADDR=http://127.0.0.1:8200
export VAULT_TOKEN=s.XXXXXXX  # Use the actual root token
🐍 Python Client Usage
📦 Install dependencies
bash
Copy
Edit
pip install requests
▶️ Run
bash
Copy
Edit
python vault_kv_client.py
This will:

Write a secret to /secret/data/demo

Read it back

List available keys

Delete the secret

💡 Customize
You can import the class in your own scripts:

python
Copy
Edit
from vault_kv_client import VaultKVClient

client = VaultKVClient()
client.create_or_update("myapp/creds", {"user": "admin", "pass": "vault123"})
print(client.read("myapp/creds"))
☕ Java Client Usage
📦 Dependencies (Gradle)
groovy
Copy
Edit
dependencies {
    implementation 'com.squareup.okhttp3:okhttp:4.12.0'
}
▶️ Build & Run
bash
Copy
Edit
javac -cp .:okhttp-4.12.0.jar VaultKVClient.java
java -cp .:okhttp-4.12.0.jar VaultKVClient
This performs the same actions as the Python client: create, read, list, and delete a test secret.

🔁 API Mapping (Vault KV v2)
Operation	HTTP Method	API Endpoint
Create/Update	POST	/v1/secret/data/<path>
Read	GET	/v1/secret/data/<path>
List	LIST | GET	/v1/secret/metadata/<prefix>?list=true
Soft Delete	DELETE	/v1/secret/metadata/<path>
Hard Delete	DELETE	/v1/secret/data/<path>

🧪 Sample Output
bash
Copy
Edit
Write: 200 OK
READ → {'username': 'alice', 'pwd': 's3cr3t'}
LIST → ['demo']
Delete: 204 No Content
✅ Tips
Vault dev mode does not persist secrets between restarts.

If using a production Vault:

Set up proper auth (AppRole, GitHub, etc.)

Mount the KV engine with: vault secrets enable -path=secret kv-v2

Adjust the base path if not using /secret

📂 Project Structure
bash
Copy
Edit
vault-kv-api/
├── vault_kv_client.py      # Python API client
├── VaultKVClient.java      # Java API client
├── README.md               # This file
📄 License
MIT License – use freely with credit.
HashiCorp Vault is © HashiCorp under BUSL-1.1 / MPL.

🙋 Questions or Suggestions?
Open an issue or reach out if you'd like support for:

Token renewal / login

TLS (HTTPS)

KV version rollback / undelete

Integration with other secret engines

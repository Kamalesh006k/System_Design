# ⛩️ API Gateway & Rate Limiting: Deep Dive

The **API Gateway** is the single entry point for all clients. It sits between the Client and the Microservices.

---

## 1. What does it do?
*   **Authentication/Authorization:** "Is this user logged in?" (JWT validation).
*   **SSL Termination:** Decrypts HTTPS traffic so internal services can speak plain HTTP (faster).
*   **Rate Limiting:** Stops abuse.
*   **Protocol Translation:** Converts HTTP frontend request to gRPC backend request.
*   **Caching:** Caches frequent responses.

---

## 2. Rate Limiting Algorithms
Prevents Denial of Service (DoS) attacks and API abuse.

### Token Bucket (Most Common)
*   **Concept:** A bucket holds `N` tokens. Refills at rate `R` tokens/sec.
*   **Action:** Every request consumes a token. If bucket empty -> 429 Too Many Requests.
*   **Pro:** Allows "Bursts" of traffic (if bucket is full).

### Leaky Bucket
*   **Concept:** Requests enter a queue. They are processed at a constant, fixed rate.
*   **Action:** If queue full -> Drop request.
*   **Pro:** Smooths out traffic spikes completely.

### Fixed Window Counter
*   **Concept:** "100 requests per minute."
*   **Flaw:** Can allow 200 requests at the "edge" of a minute (100 at 00:59, 100 at 01:00).

### Sliding Window Log
*   **Concept:** Keeps a timestamp log of every request.
*   **Pro:** Perfectly accurate.
*   **Con:** High memory usage (must store every timestamp).

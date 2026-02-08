# 📡 Communication Protocols: Deep Dive

How do microservices talk to each other?

---

## 1. HTTP/REST (Representational State Transfer)
*   **Format:** Text (JSON/XML).
*   **Transport:** TCP.
*   **Pros:** Readable, Firewall friendly, Standard (Browser compatible).
*   **Cons:** Uneconomical (Bloated headers), No built-in streaming.
*   **Use Case:** Best for Public APIs (External Clients).

---

## 2. gRPC (Remote Procedure Call)
*   **Format:** Binary (Protobuf).
*   **Transport:** HTTP/2.
*   **Pros:** Extremely fast (small payload), Type-safe (Contract based), Bi-directional streaming.
*   **Cons:** Not human readable, requires code generation.
*   **Use Case:** Best for **Internal Microservices** communication (Service A to Service B).

---

## 3. WebSockets
*   **Format:** Binary/Text frames over a persistent TCP connection.
*   **Transport:** Starts as HTTP Handshake, upgrades to persistent TCP.
*   **Pros:** Real-time, Low latency, Bi-directional.
*   **Cons:** Stateful (Hard to load balance, server must hold open connection).
*   **Use Case:** Chat apps, Live sports updates, Stock tickers.

---

## 4. Long Polling (The "Fake" Socket)
*   **Mechanism:** Client sends request. Server *holds* request open until data is available or timeout occurs.
*   **Pros:** Works everywhere (standard HTTP).
*   **Cons:** Server resource heavy (holding open threads).
*   **Use Case:** When WebSockets are blocked by corporate firewalls.

---

## 5. GraphQL
*   **Format:** JSON.
*   **Mechanism:** Client asks for *exactly* what fields it needs.
*   **Pros:** Prevents Over-fetching (getting too much data) and Under-fetching (getting too little).
*   **Cons:** Complexity on the backend, Caching is hard (since every query is different).
*   **Use Case:** Mobile apps with strictly limited bandwidth.

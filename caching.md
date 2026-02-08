# ⚡ Caching: Deep Dive

**Caching** takes advantage of the "Locality of Reference" principle: recently used data is likely to be used again.

---

## 1. Where to Cache?
1.  **Browser:** Local storage, Cookies.
2.  **CDN:** Static assets at the edge.
3.  **Load Balancer:** SSL termination, simple responses.
4.  **Application:** In-memory objects (local variables).
5.  **Database:** Internal DB buffer pool.
6.  **Distributed Cache:** Redis/Memcached (The most common "System Design" answer).

---

## 2. Caching Strategies
*   **Cache-Aside (Lazy Loading):**
    *   App checks Cache. Miss? App reads DB -> Writes to Cache -> Returns to User.
    *   *Pro:* Only requests data that is needed.
    *   *Con:* First request is always slow (Cache miss).
*   **Write-Through:**
    *   App writes to Cache and DB synchronously.
    *   *Pro:* Data consistency is high.
    *   *Con:* Writes are slower (2 network calls).
*   **Write-Back (Write-Behind):**
    *   App writes to Cache. Cache writes to DB asynchronously.
    *   *Pro:* Extremely fast writes.
    *   *Con:* Data loss risk availability if Cache crashes before syncing.

---

## 3. Eviction Policies
When the cache is full, who gets kicked out?
*   **LRU (Least Recently Used):** The standard. Removes items not accessed for the longest time.
*   **LFU (Least Frequently Used):** Removes items with the fewest total hits. Good for "all-time classics."
*   **FIFO (First In First Out):** Oldest item leaves. Dumb but fast.

---

## 4. The Thundering Herd Problem
*   **Scenario:** A popular cache item expires. Suddenly, 10,000 users hit the DB at the exact same millisecond to fetch it.
*   **Solution:** Mutual Exclusion (Mutex) locks, or pre-refreshing the cache key before it fully expires.

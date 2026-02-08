# 🔎 Database Indexing: Deep Dive

An **index** is a data structure that improves the speed of data retrieval operations on a database table at the cost of additional writes and storage space.

---

## 1. How Indexes Work
Imagine a phonebook that isn't sorted alphabetically. Finding "John Smith" would require reading every single name (**O(N)** complexity). If it's sorted, you can use Binary Search (**O(log N)**). An index *is* that sorted copy.

---

## 2. Common Index Structures

### B-Trees (Balanced Trees)
*   **Mechanism:** A tree structure that keeps data sorted and allows searches, sequential access, insertions, and deletions in logarithmic time.
*   **Use Case:** The default for almost all SQL databases (MySQL, Postgres). Great for range queries (`WHERE age > 18`).

### Hash Indexes
*   **Mechanism:** Uses a hash table.
*   **Use Case:** Exact match queries (`WHERE id = 123`). Cannot handle range queries. fast lookups **O(1)**.

### LSM Trees (Log-Structured Merge-Tree)
*   **Mechanism:** Optimizes for write-heavy workloads by appending data to a log and merging later.
*   **Use Case:** NoSQL databases like Cassandra and RocksDB.

---

## 3. Index Types
1.  **Primary Index:** The unique identifier (e.g., UserID).
2.  **Secondary Index:** Non-unique fields (e.g., Email, Age).
3.  **Composite Index:** An index on multiple columns (`(Lastname, Firstname)`).
    *   *Rule:* Order matters! An index on `(A, B)` can help query `A` but NOT query `B` alone.

---

## 4. The Trade-off
*   **Reads:** **FASTER**. The DB goes straight to the record.
*   **Writes:** **SLOWER**. The DB must write the new data row AND update the B-Tree structure.
*   **Storage:** **HIGHER**. Indexes take up disk space.

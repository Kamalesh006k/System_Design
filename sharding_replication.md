# 🧩 Sharding & Replication: Deep Dive

Scaling a database involves two main techniques: **Replication** (copying) and **Sharding** (splitting).

---

## 1. Replication (The Copy-Paste Strategy)
*   **Goal:** Increase Read Throughput and Reliability.
*   **Master-Slave Architecture:**
    *   **Master:** Handles all **WRITES** (INSERT, UPDATE, DELETE). Travels to Slaves.
    *   **Slaves:** Handle only **READS** (SELECT).
    *   *Pro:* Offloads read traffic.
    *   *Con:* Replication Lag (Slaves might be a few ms behind Master).
*   **Master-Master Architecture:**
    *   Both nodes can accept Writes.
    *   *Pro:* High availability for writes.
    *   *Con:* Conflict resolution is a nightmare (Step 1 writes X=1, Step 2 writes X=2 at same time).

---

## 2. Sharding (The Divide-and-Conquer Strategy)
*   **Goal:** Increase Write Throughput and Storage Capacity.
*   **Mechanism:** Splitting a large dataset into smaller chunks called **Shards**.
*   **Sharding Key:** The logic used to split data.
    *   **Range Based:** `UserIDs 1-1000` -> Shard A. `1001-2000` -> Shard B.
        *   *Problem:* Uneven distribution (Hotspots).
    *   **Hash Based:** `hash(UserID) % 4` -> Shard [0, 1, 2, 3].
        *   *Problem:* Adding a new shard requires moving data (Resharding).
    *   **Geo-Based:** `US Data` -> US Shard. `EU Data` -> EU Shard.

---

## 3. Sharding Challenges
1.  **Joins:** You cannot easily JOIN tables across different shards. You must do it in application code.
2.  **Transactions:** ACID transactions across shards ("Distributed Transactions") are slow and complex (Two-Phase Commit).
3.  **Resharding:** Extremely difficult to do without downtime.

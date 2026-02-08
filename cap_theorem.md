# ⚖️ CAP Theorem: Deep Dive

In 1998, Eric Brewer proved that it is impossible for a distributed data store to simultaneously provide more than two out of the following three guarantees.

---

## 1. The Three Properties

### Consistency (C)
*   **Definition:** Every read receives the most recent write or an error.
*   **Meaning:** All nodes see the same data at the same time. If Node A updates `x=5`, Node B must instantly see `x=5`.

### Availability (A)
*   **Definition:** Every request receives a (non-error) response, without the guarantee that it contains the most recent write.
*   **Meaning:** The system is always up. If Node A is down, Node B still answers (even if its data is old).

### Partition Tolerance (P)
*   **Definition:** The system continues to operate despite an arbitrary number of messages being dropped or delayed by the network between nodes.
*   **Meaning:** The system works even if the network cable is cut between the US and EU data centers.

---

## 2. The Trade-off (You Must Pick Two)
Since networks *will* fail (Partition happens), you must choose between **CP** and **AP**.

### CP (Consistency + Partition Tolerance)
*   **Behavior:** "I'd rather return an ERROR than return old data."
*   **Use Case:** Banking (Balance transfers), Inventory management.
*   **Example:** MongoDB, HBase, Redis (mostly).

### AP (Availability + Partition Tolerance)
*   **Behavior:** "I'd rather return OLD data than simply fail."
*   **Use Case:** Social Media (Like counts), Shopping Carts.
*   **Example:** Cassandra, DynamoDB, CouchDB.

### CA (Consistency + Availability)
*   **Behavior:** "I guarantee perfect data and perfect uptime, but if the network fails, I die."
*   **Reality:** This is impossible in distributed systems. It only exists in single-node databases (like a local MySQL instance).

---

## 3. PACELC Theorem (The Extension)
CAP only talks about failures. What about when the system is running normally?
**PACELC** states:
*   If there is a partition (**P**), how does the system trade off availability and consistency (**A** and **C**)?
*   **E**lse (**E**), when the system is running normally, how does it trade off latency (**L**) and consistency (**C**)?
*   *Example:* DynamoDB gives up consistency (Eventual Consistency) for low latency.

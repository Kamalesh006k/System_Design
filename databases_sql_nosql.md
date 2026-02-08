# 🗄️ Databases: SQL vs NoSQL Deep Dive

Choosing the right database is the single most critical decision in system design.

---

## 1. Relational Databases (SQL / RDBMS)
*   **Structure:** Tables with fixed columns and rows.
*   **Schema:** Rigid. You must define what data looks like *before* inserting it.
*   **Key Feature: ACID Transactions**
    1.  **Atomicity:** All or nothing. If one part fails, the whole transaction rolls back.
    2.  **Consistency:** The DB goes from one valid state to another valid state.
    3.  **Isolation:** Transactions don't interfere with each other.
    4.  **Durability:** Once committed, data is saved forever (even if power fails).
*   **Scaling:** Primarily **Vertical**. Reads can be scaled horizontally (Read Replicas), but writes are hard to scale.
*   **Best For:** Financial systems, e-commerce orders, strict data relationships.
*   **Examples:** PostgreSQL, MySQL, Oracle, SQL Server.

---

## 2. NoSQL Databases
*   **Structure:** Flexible. Can be Key-Value, Document, Column, or Graph.
*   **Schema:** Dynamic. You can add new fields on the fly.
*   **Key Feature: BASE**
    1.  **Basically Available:** System guarantees availability.
    2.  **Soft state:** The state of the system may change over time.
    3.  **Eventual consistency:** The system will eventually become consistent once it stops receiving inputs.
*   **Scaling:** Built for **Horizontal Scaling**. Sharding is native.
*   **Best For:** Big Data, Real-time analytics, Content Management, Social Networks.

---

## 3. Types of NoSQL
1.  **Key-Value:**
    *   *Examples:* Redis, DynamoDB.
    *   *Use Case:* Caching, User Sessions, Shopping Carts.
2.  **Document:**
    *   *Examples:* MongoDB, CouchDB.
    *   *Use Case:* CMS, Catalogs, User Profiles.
3.  **Column-Family:**
    *   *Examples:* Cassandra, HBase.
    *   *Use Case:* Time-series data, heavy write loads (logs).
4.  **Graph:**
    *   *Examples:* Neo4j.
    *   *Use Case:* Social networks (friend of a friend), Recommendation engines.

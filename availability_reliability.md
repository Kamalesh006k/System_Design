# 🛡️ Availability & Reliability: Deep Dive

While often used interchangeably, these are distinct metrics that define the "trustworthiness" of your system.

---

## 1. Reliability
*   **Definition:** The probability that a system performs its required functions without failure for a specific time period.
*   **Key Metric:** **MTBF (Mean Time Between Failures)**.
*   **Goal:** To prevent bugs, data corruption, and logic errors.
*   **Analogy:** A car that never breaks down on a road trip is *reliable*.

## 2. Availability
*   **Definition:** The proportion of time a system is in a functioning condition.
*   **Key Metric:** **Uptime %** or **MTTR (Mean Time To Recovery)**.
*   **Goal:** To handle failures effectively so the user doesn't notice.
*   **Analogy:** A car that breaks down but is fixed by a mechanic in 30 seconds is *available*.

---

## 3. The "Nines" of Availability

| "Nines" | Availability % | Downtime per Year | Downtime per Day |
| :--- | :--- | :--- | :--- |
| **Two Nines** | 99% | 3.65 days | 14.4 mins |
| **Three Nines** | 99.9% | 8.76 hours | 1.44 mins |
| **Four Nines** | 99.99% | 52.6 mins | 8.64 secs |
| **Five Nines** | 99.999% | 5.26 mins | 0.86 secs |

> [!NOTE]
> **Five Nines (99.999%)** is the "Gold Standard" for critical systems like telecommunications or banking.

---

## 4. SLA vs SLO vs SLI
*   **SLI (Indicator):** What are we measuring? (e.g., Latency, Error Rate).
*   **SLO (Objective):** What is our target? (e.g., "Latency should be < 200ms").
*   **SLA (Agreement):** What happens if we miss the target? (e.g., "We pay you back 10%").

---

## 5. Fault Tolerance
*   **Definition:** The ability of a system to continue operating without interruption when one or more of its components fail.
*   **Strategy:** Redundancy.
    *   **Active-Passive:** One server works, one sleeps. If Active dies, Passive wakes up.
    *   **Active-Active:** Both servers work. If one dies, the other takes 100% load.

# 📞 DNS (Domain Name System): Deep Dive

**DNS** is the phonebook of the internet. It translates human-friendly hostnames (`www.example.com`) into computer-friendly IP addresses (`192.0.2.1`).

---

## 1. How DNS Works (The Journey)
When you type `google.com`:
1.  **Browser Cache:** "Have I visited this recently?"
2.  **OS Cache:** "Does my computer know this?"
3.  **Resolver (ISP):** Your Internet Provider looks it up.
4.  **Root Server (`.`):** "I don't know, but here is the `.com` TLD server."
5.  **TLD Server (`.com`):** "I don't know the IP, but here is Google's Name Server."
6.  **Authoritative Name Server:** "Here is the IP: `1.2.3.4`."

---

## 2. DNS Record Types

| Type | Full Name | Use Case | Example |
| :--- | :--- | :--- | :--- |
| **A** | Address | Maps Hostname -> UDP IPv4 | `example.com` -> `1.2.3.4` |
| **AAAA** | Address (v6) | Maps Hostname -> IPv6 | `example.com` -> `2001:db8::1` |
| **CNAME** | Canonical Name | Maps Hostname -> Hostname (Alias) | `www.example.com` -> `example.com` |
| **MX** | Mail Exchange | Directs email traffic | `example.com` -> `mail.google.com` |
| **TXT** | Text | Arbitrary text (Verification, SPF, DKIM) | `google-site-verification=...` |
| **NS** | Name Server | Points to the DNS server for the zone | `example.com` -> `ns1.aws.com` |

---

## 3. TTL (Time To Live)
*   **Definition:** The amount of time (in seconds) a DNS record is cached by resolvers/browsers.
*   **High TTL (e.g., 24 hours):**
    *   *Pros:* Less traffic to your DNS server; faster for users (cached).
    *   *Cons:* If you change server IPs, users won't see the change for 24 hours.
*   **Low TTL (e.g., 60 seconds):**
    *   *Pros:* Changes propogate instantly.
    *   *Cons:* Heavy load on DNS servers; slightly slower lookup for users.

---

## 4. Routing Strategies
*   **GeoDNS:** Routes users to the closest IP based on their location (e.g., US users -> US IP, EU users -> EU IP).
*   **Round Robin DNS:** Returns a list of IPs that rotates. Poor man's load balancing.

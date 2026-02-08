# 🚚 CDN (Content Delivery Network): Deep Dive

A **CDN** is a geographically distributed network of proxy servers and their data centers. The goal is to provide high availability and performance by distributing the service spatially relative to end-users.

---

## 1. Why use a CDN?
*   **Latency:** Speed of light matters. Serving a user in London from a server in Sydney takes ~300ms. Serving them from a London CDN edge takes ~5ms.
*   **Bandwidth:** Offloads huge traffic (images/videos) from your main servers.
*   **Security:** CDNs act as a shield against **DDoS attacks**.

---

## 2. How it Works
1.  User requests `image.jpg`.
2.  DNS routes user to the nearest **Edge Server** (Point of Presence - PoP).
3.  **Cache Hit:** If the Edge has the image, it serves it instantly.
4.  **Cache Miss:** If not, the Edge fetches it from your **Origin Server**, saves it, and serves it.

---

## 3. Push vs Pull CDNs
*   **Pull CDN (Standard):**
    *   The CDN fetches content *only* when a user requests it.
    *   *Best for:* Websites with lots of traffic but uncertain popularity patterns.
*   **Push CDN:**
    *   You explicitly upload content to the CDN before anyone asks for it.
    *   *Best for:* Large files like Software Updates or Movie Releases.

---

## 4. Cache Invalidation (The Hard Part)
"There are only two hard things in Computer Science: cache invalidation and naming things."

*   **TTL (Time To Live):** Content expires after X minutes.
*   **Purge:** You manually tell the CDN "Delete `banner.jpg` everywhere!" (Expensive/Slow).
*   **Versioning:** Change the filename (`banner_v2.jpg`). The CDN treats it as a new file. *Recommended Strategy.*

---

## 5. Popular CDNs
*   **Cloudflare:** Famous for security features (DDoS protection).
*   **AWS CloudFront:** Deep integration with S3/EC2.
*   **Akamai:** The enterprise giant; mostly for huge media companies.

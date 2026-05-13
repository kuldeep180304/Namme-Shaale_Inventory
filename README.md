# 🏫 Namma-Shaale Inventory

Namma-Shaale Inventory is a "Digital Asset Auditor" designed for primary and secondary schools. It provides a simplified, mobile-first approach to tracking the health and condition of government-funded resources like sports kits, lab equipment, and tablets.

---

## 📌 Table of Contents
- [Problem Statement](#1-the-problem-statement)
- [Vision](#2-the-vision)
- [Key Features & User Flow](#3-key-features--user-flow)
- [Technical Implementation](#4-technical-implementation)
- [App Screenshots](#5-app-screenshots)
- [Impact Goals](#6-impact-goals)
- [Success Criteria](#7-success-criteria)
- [How to Run](#how-to-run)

---

## 1. The Problem Statement

<details>
<summary>Click to expand</summary>

Schools often receive valuable equipment, but there is rarely an efficient way to track its "Health" over time. Broken tablets or lost sports gear are often only discovered months later during infrequent manual audits. This app bridges that gap by allowing teachers to perform quick, regular audits.

</details>

---

## 2. The Vision

<details>
<summary>Click to expand</summary>

The app serves as a digital ledger to ensure every resource is used and maintained properly. By "Tagging" and "Scanning" items, teachers can maintain a real-time record of what is working, what needs repair, and what is broken.

</details>

---

## 3. Key Features & User Flow

<details>
<summary>Click to view features</summary>

- Asset Dashboard: A high-level view of school inventory, categorized by "Working," "Needs Repair," and "Broken" status.
- Asset Register: Easily add new items (e.g., "Microscope") including Serial Numbers and Categories.
- Photo Documentation: Uses integrated camera features to capture the physical condition of high-value items at the time of registration.
- Condition Tracking: Monthly "Health Checks" where teachers can update the status of an item (Green/Yellow/Red) in seconds.
- Issue Log: A simple way to track why an item is no longer functional (e.g., "lost during match").

</details>

---

## 4. Technical Implementation

<details>
<summary>Click to expand tech stack</summary>

- Language: Kotlin / Java (Android)
- Database: Room DB for persistent storage of asset lists and health check history.
- UI Architecture: Material Design dashboard for scannable data visualization.
- Hardware Integration: CameraX API for documenting the condition of assets through photos.

</details>

---

## 5. App Screenshots

<details>
<summary>View Screens</summary>

### Dashboard
<img width="250" src="https://github.com/user-attachments/assets/22505fda-fee0-43bc-a8a7-0220aa9de622"/>

---

### All Assets List
<img width="250" src="https://github.com/user-attachments/assets/22505fda-fee0-43bc-a8a7-0220aa9de622"/>

---

### Register Asset
<img width="250" src="https://github.com/user-attachments/assets/22505fda-fee0-43bc-a8a7-0220aa9de622"/>

</details>

---

## 6. Impact Goals

<details>
<summary>Click to expand impact</summary>

- Resource Optimization: Ensuring taxpayer money spent on school kits is well-tracked and preserved.
- Educational Quality: Keeping science labs and sports rooms functional for students by identifying repair needs early.
- Accountability: Building a culture of "Asset Care" within the public school system through transparent digital records.

</details>

---

## 7. Success Criteria

<details>
<summary>Click to expand success metrics</summary>

- Speed: The "Monthly Health Check" is designed to allow a teacher to update 10 items in under 2 minutes.
- Organization: A clean, professional UI that categorizes equipment logically for non-technical users.
- Reliability: Offline-first capability using Room DB to ensure data is saved even without a stable school internet connection.

</details>

---

## 🚀 How to Run

<details>
<summary>Setup Instructions</summary>

Clone the repository:

```bash
git clone https://github.com/kuldeep180304/Namme-Shaale_Inventory.git

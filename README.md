#Namma-Shaale Inventory 🏫
Namma-Shaale Inventory is a "Digital Asset Auditor" designed for primary and secondary schools. It provides a simplified, mobile-first approach to tracking the health and condition of government-funded resources like sports kits, lab equipment, and tablets.

1. The Problem Statement
Schools often receive valuable equipment, but there is rarely an efficient way to track its "Health" over time. Broken tablets or lost sports gear are often only discovered months later during infrequent manual audits. This app bridges that gap by allowing teachers to perform quick, regular audits.

2. The Vision
The app serves as a digital ledger to ensure every resource is used and maintained properly. By "Tagging" and "Scanning" items, teachers can maintain a real-time record of what is working, what needs repair, and what is broken.

3. Key Features & User Flow
Asset Dashboard: A high-level view of school inventory, categorized by "Working," "Needs Repair," and "Broken" status.

Asset Register: Easily add new items (e.g., "Microscope") including Serial Numbers and Categories.

Photo Documentation: Uses integrated camera features to capture the physical condition of high-value items at the time of registration.

Condition Tracking: Monthly "Health Checks" where teachers can update the status of an item (Green/Yellow/Red) in seconds.

Issue Log: A simple way to track why an item is no longer functional (e.g., "lost during match").

4. Technical Implementation
Language: Kotlin / Java (Android)

Database: Room DB for persistent storage of asset lists and health check history.

UI Architecture: Material Design dashboard for scannable data visualization.

Hardware Integration: CameraX API for documenting the condition of assets through photos.

5. App Screenshots
Dashboard	All Assets List	Register Asset
6. Impact Goals
Resource Optimization: Ensuring taxpayer money spent on school kits is well-tracked and preserved.

Educational Quality: Keeping science labs and sports rooms functional for students by identifying repair needs early.

Accountability: Building a culture of "Asset Care" within the public school system through transparent digital records.

7. Success Criteria
Speed: The "Monthly Health Check" is designed to allow a teacher to update 10 items in under 2 minutes.

Organization: A clean, professional UI that categorizes equipment logically for non-technical users.

Reliability: Offline-first capability using Room DB to ensure data is saved even without a stable school internet connection.

How to Run
Clone the repository:

Bash
git clone https://github.com/kuldeep180304/Namme-Shaale_Inventory.git
Open the project in Android Studio.

Build and run the app on an emulator or physical device (API 24+ recommended).

Does this look like it covers everything you've implemented so far?

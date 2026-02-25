# Individual Assignment 3 – Jetpack Compose Layouts

**Student:** SongYu Chen  
**Repository:** git@github.com:songyuchen2584/IndividualAssignment3.git

---

## Overview

This project demonstrates Jetpack Compose layout techniques including:

- Row and Column layouts
- Box layering
- FlowRow and FlowColumn
- Responsive layouts

The application contains a Home Screen that allows navigation to four required screens.

Minimum SDK: 26

---

## How to Run

1. Open the project in Android Studio
2. Sync Gradle
3. Run on emulator or device

---

# Q1 — Row/Column Mastery: Settings Screen

## Layout Requirements

The Settings screen uses:

- A **Column** as the main layout container

Each setting row uses:

- A **Row** layout  
- Left side:
  - Label + supporting text inside a **Column**
- Right side:
  - Switch control

`Modifier.weight()` is used on the text column to prevent truncation and keep controls aligned.

## Material 3 Components Used (6+)

- TopAppBar  
- Card  
- Switch  
- Divider  
- ListItem  
- IconButton  
- Snackbar  

## Modifiers Used

Required modifiers:

- padding  
- fillMaxWidth  
- weight  
- heightIn  
- sizeIn  
- align  

Additional modifiers:

- border  
- background  
- clickable  

## Screenshot
<img width="480" height="859" alt="image" src="https://github.com/user-attachments/assets/2612c3c0-b0ae-40e4-a757-6732df5b197b" />


## AI Usage Disclosure (Q1)

AI assistance was used to help outline the Row/Column structure and confirm required modifiers/components were included. Final implementation and edits were done myself.

---

# Q2 — Box Layout: Profile Header + Overlay Card

## Layout Requirements

The profile screen header uses a **Box** layout:

- Background created using Box + background modifier  
- Circular avatar in foreground  
- Card overlay partially overlapping the header

Alignment techniques used:

- contentAlignment  
- align(...)  
- offset(...)  

## Material 3 Components Used (5+)

- TopAppBar  
- Card  
- Button  
- FilledTonalButton  
- AssistChip  
- Badge  
- Surface  

## Modifiers Used

Required modifiers:

- clip(CircleShape) for avatar  
- offset for overlay effect  
- shadow for elevation  
- aspectRatio for card layout  
- fixed size for avatar  

## Screenshot
<img width="479" height="858" alt="image" src="https://github.com/user-attachments/assets/4e5a53b2-2e2f-4636-8eca-a6dfbeb8c1ba" />


## AI Usage Disclosure (Q2)

AI assistance was used to propose a minimal Box layering approach (header, avatar, overlay card) and to verify required modifier techniques. Final implementation and debugging were done by myself.

---

# Q3 — FlowRow / FlowColumn: Tag Browser

## Layout Requirements

The Tag Browser screen uses:

- **FlowRow** for wrapping tags across the screen

A second section uses **FlowColumn**:

- Selected tags area displays selected chips
- Selected tags wrap into multiple columns when needed

The Selected Tags section updates when chips are tapped.

## Material 3 Components Used

Required:

- FilterChip

Additional (4+):

- AssistChip  
- Card  
- Divider  
- Text  
- TopAppBar  

## Modifiers Used

- Arrangement.spacedBy() for consistent spacing  
- fillMaxWidth for responsive sizing  
- padding for layout spacing  

Selected state visualization:

- FilterChip selected state changes  
- Border changes when selected  

## Screenshot
<img width="452" height="850" alt="image" src="https://github.com/user-attachments/assets/6f2994f1-d966-4001-8b8b-96ec498f1fd4" />


## AI Usage Disclosure (Q3)

AI assistance was used to suggest a simple state-driven selected-tags pattern and to confirm FlowRow/FlowColumn requirements. I completed the final implementation and debugged with logs from logcat.

---

# Q4 — Responsive Layout Challenge: Phone vs Tablet

## Layout Requirements

Responsive behavior implemented using **BoxWithConstraints**.

### Phone Layout (Narrow Screens)

- Single **Column** layout

### Tablet Layout (Wide Screens)

- **Row** layout with two panes

Left pane:

- NavigationRail
- Options list using Column

Right pane:

- Detail panel using Box + Column

## Material 3 Components Used (6+)

Required:

- NavigationRail

Additional:

- TopAppBar  
- NavigationRailItem  
- Card  
- ListItem  
- Divider  
- Text  

## Modifiers Used

- weight() used to allocate space between panes  
- fillMaxHeight() used for panes  

Scrolling:

- LazyColumn used for vertical scrolling  

## Screenshots
<img width="475" height="853" alt="image" src="https://github.com/user-attachments/assets/3fc23a20-95a4-41fe-a41b-b8f71f526ed3" />

<img width="1064" height="744" alt="image" src="https://github.com/user-attachments/assets/73dcb14a-cf51-4f55-9a0a-4a0704ae6496" />


## AI Usage Disclosure (Q4)

AI assistance was used to recommend a minimal responsive breakpoint approach and a two-pane layout structure. Final implementation and edits were done by me.

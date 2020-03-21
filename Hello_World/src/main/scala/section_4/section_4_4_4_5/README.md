## CQRS Pattern


---    ← → Queries Services ← Read →  
|  |
|UI|                                Storage
|  |
---   ← → Command Service ← Write →

Command Query Responsibility Segregation: Seperation between read and write.

Persistence query will handle the read from a journal

## Predfined querues

### read qll events: 
* All persistence id
* current persisten id

### Read events for specific persistent actor
* event by persistence id
* current event by persistence id

### Read all events based on Tags
* Events by tag
* Current events by tag
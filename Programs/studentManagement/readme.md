# Student Management System — Overview & Guide

This document describes the Student Management System (CLI) included in this project. It explains the concept, learning objectives, system design, validation rules, how to build and run the program, and how to extend or test it.

---

## What is this project?

A small command-line Student Management System (SMS) used to practice Java programming concepts: object modeling, input handling, validation, collections, and simple business rules. The CLI lets you add, view, update, delete and list students kept in memory. The system enforces basic validations (age, email format) and uniqueness rules (ID, email, and name).

Files of interest
- Programs/studentManagement/Student.java — student data model, validation logic
- Programs/studentManagement/StudentManager.java — in-memory storage + business rules (add/update/delete/find)
- Programs/studentManagement/studentManagementSystem.java — command line interface (menu, prompts)
- (Optional) Programs/studentManagement/TestEmailValidation.java — small test script demonstrating email validation

---

## Learning objectives

By reading and running this project you will learn to:
- Model domain data using classes (Student).
- Encapsulate validation logic in setters/constructors.
- Design a simple manager/service (StudentManager) to enforce collection-level invariants (unique id, email, name).
- Build a robust console UI that collects input safely and handles exceptions.
- Normalize and compare strings (case-insensitive name/email comparison).
- Write simple unit-style test scripts for focused behavior (e.g., email validation).
- Reason about tradeoffs: linear search vs indexing for uniqueness checks.

---

## High-level design

1. Student (data model)
   - Fields: id, name, age, email, faculty.
   - Validation:
     - Age: allowed range (e.g., 15–23).
     - Email: syntactic validation via regex and (in current demo) a rule to accept only `@gmail.com` addresses (to demonstrate stricter domain enforcement).
   - Validation is performed in setters and invoked by the constructor.

2. StudentManager (service layer)
   - Stores students in a Map keyed by student ID for fast lookup.
   - Provides operations:
     - addStudent(Student): enforces uniqueness (id, email, name) and returns boolean success.
     - updateStudent(Student): ensures updates do not violate uniqueness.
     - deleteStudent(id), findById(id), findByEmail(email), findByName(name), listAll().
   - Email and name comparisons are normalized consistently (trim + toLowerCase).

3. CLI (studentManagementSystem)
   - Presents a simple menu loop for CRUD operations.
   - Uses prompt helpers to read required or optional inputs.
   - Calls StudentManager methods; catches IllegalArgumentException from Student setters and shows friendly messages.
   - Prevents duplicate IDs/emails/names before constructing/adding students and during updates.

---

## Business rules enforced

- ID must be non-null and non-empty. IDs are unique.
- Age must be within the accepted student range (currently 15–23 inclusive/exclusive depending on implementation; follow the code).
- Email must match the configured regex and (in the demo) must end with `@gmail.com`. You can relax or change this rule in `Student.setEmail(...)`.
- Email uniqueness: no two students may share the same email (case-insensitive, trimmed).
- Name uniqueness: exact match after trimming and lower-casing is considered a duplicate (so `Chin` and `CHIN` are duplicates; `Chin Er` is not).

---

## How it works (user flow)

Add student:
1. Select "Add Student" in the menu.
2. Enter Name, Age, Email, Faculty, and ID (ID may be suggested if coming from an update flow).
3. CLI checks:
   - Name uniqueness (case-insensitive).
   - ID uniqueness.
   - Email uniqueness.
4. If all checks pass, it attempts to build a Student object. The Student constructor/setters validate age and email syntax/domain and will throw an IllegalArgumentException for invalid values. The CLI catches this and prints a message such as `Invalid Input: Email format is invalid.`

Update student:
1. Select "Update Student", enter the student ID.
2. If ID not found, the CLI offers to add a new student using that ID.
3. When updating, empty input values are interpreted as "keep current".
4. When changing email or name, uniqueness checks prevent conflicts with other students.

View, Delete, List:
- View by ID prints stored student details.
- Delete removes the student (and the stored email/name consequently become available for new entries).
- List prints all students in the manager.

---

## How to build & run

From your project root (where `Programs/` is a subdirectory) using the JDK:

1. Compile:
   javac Programs/studentManagement/*.java

2. Run the CLI:
   java Programs.studentManagement.studentManagementSystem

3. (Optional) Run the email validation test:
   java Programs.studentManagement.TestEmailValidation

Notes:
- The code uses only the Java standard library.
- The program stores students in memory only; data is lost when the program exits. To persist data add file I/O or a database layer.

---

## Example runs

1) Add with invalid gmail TLD typo:
- Input: email = `user@gmail.xom`
- Behavior: Student constructor rejects the email and CLI prints:
  `Invalid Input: Only gmail.com address are accepted.`

2) Duplicate name behavior:
- Add Student A: Name = `Chin`
- Attempt to add Student B: Name = `CHIN` → CLI prints:
  `User cannot have duplicate name.` (because comparison is case-insensitive)

3) Update prevents conflicts:
- When changing an email or name, the update flow checks whether another student already owns that email/name and aborts the update with a message when a conflict exists.

---

## Where the key validations live

- Email format & domain: `Programs/studentManagement/Student.java` → `setEmail(...)`
- Age validation: `Student.setAge(...)`
- Uniqueness checks (ID, email, name): `Programs/studentManagement/StudentManager.java`
- CLI orchestration & friendly messages: `Programs/studentManagement/studentManagementSystem.java`

---

## How to change the email rule

- Currently the demo enforces gmail-only addresses. To allow other domains:
  - Edit `Student.setEmail(...)`.
  - Either remove the domain check (so only regex is used) or replace the domain check with a TLD whitelist or a different policy.
  - Make sure `StudentManager.findByEmail(...)` normalizes the email the same way as `Student.setEmail(...)` (trim and lower-case) so uniqueness checks are consistent.

---

## Extending the system

Ideas to extend and practice:
- Persist students to disk (CSV, JSON) and load at startup.
- Replace linear `findByEmail`/`findByName` with secondary indexes (Map<String,id>) for O(1) lookups.
- Add unit tests (JUnit) for Student validation, StudentManager behaviors, and CLI helpers.
- Add search/filter by faculty or age range.
- Add role-based access or an admin password for destructive operations (delete).
- Improve the CLI to accept command-line arguments or implement a simple REPL command parser.

---

## Testing tips

- Use `TestEmailValidation.java` or write JUnit tests for:
  - valid/invalid emails and domain checks,
  - add/update duplicate detection for id/email/name,
  - age boundary conditions.

- Manually test CLI flows:
  - Add a student, attempt to add another with same id/email/name (different casing), update to conflicting values, and delete.

---

## Final notes

This project is intentionally small and instructional. It highlights good practices:
- keep validation close to the data model (`Student`),
- keep collection-level rules in the manager (`StudentManager`),
- keep user interaction logic in the UI layer (`studentManagementSystem`),
- normalize strings consistently for comparisons,
- catch and report validation exceptions in the UI.

If you’d like, I can:
- Produce a README.md instead of read.md and commit it into the repo,
- Generate a TestNameDuplication.java script to demonstrate name-dup detection,
- Or implement persistent CSV save/load in StudentManager and wire import/export options into the CLI.


TODO: Write readme

Ktorm Rules:

- Only use entity api for simple queries that map directly to a table
- Use query api for everything else, including the following:
    - queries that return columns that don't map to a single table
    - queries that join multiple tables
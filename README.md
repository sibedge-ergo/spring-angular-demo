# ergo-demo-app
A SpingBoot/Angular app for demonstration purposes

To start the application locally please run `docker-compose up -d` and open `http://localhost:4200`

To remove the application run `docker-compose down -v`

Impl. notes: any string based filters (i.e. first name) support wildcards (masks) where:

| Wildcard | Definition                            | Example                                                                         |
| -------- | ------------------------------------- | ------------------------------------------------------------------------------- |
| `*`      | any string of zero or more characters | `L*` finds all 2+ letter names that start with *L* (Lee, Lucas, and so on)      |
| `?`      | any single character                  | `_ean` finds all four-letter names that end with *ean* (Dean, Sean, and so on). |

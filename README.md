# ProjectHibernate2
Second project on hibernate

Defects found in the database:

1. Need to add a foreign key to the film_text table;
2. Enum in the Rating table must comply with the rules of writing code in Java;
3. The Customer table contains different types for create_date and last_update. Nothing critical, but it's better to bring everything to the same type;
4. Why do we even need the original_language column in the film table?;
5. The special_features column in the film table requires normalization.

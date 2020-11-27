USE toolshop;

# 1. From other file

# 2. A basic retrieval query
# Displaying all suppliers
SELECT * FROM supplier;

# 3. A retrieval query with ordered results
# Displaying all tools ordered by price descending
SELECT * FROM tool ORDER BY price DESC;

# 4. A nested retrieval query
# Displaying all non-electrical tools that have less than 40 units in stock
SELECT T.tName, T.quantity, T.sName
FROM tool AS T
WHERE quantity IN 
	 (SELECT quantity
      FROM tool
      WHERE tType="non-electrical"
			AND quantity < 40);
            
# 5. A retrieval query using joined tables
# Getting all tools (from left table) and Power Type from right table, will show null if left not in right
SELECT T.tName, E.powerType 
FROM tool AS T 
LEFT OUTER JOIN electrical AS E 
ON T.toolId = E.toolId;

# 6. 
# 
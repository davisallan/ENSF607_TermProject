USE toolshop;

# 1. From other file

# 2. A basic retrieval query
# Displaying all tools that cost more than 40
SELECT T.tName, T.sName, T.price 
FROM tool AS T 
WHERE price > 40;

# 3. A retrieval query with ordered results
# Displaying tools ordered by price descending
SELECT T.tName, T.tType, T.price FROM tool AS T ORDER BY price DESC;

# 4. A nested retrieval query
# Displaying the name, price and supplier name of the tools that customer 138 has bought
SELECT T.tName, T.price, T.sName
FROM tool AS T
WHERE T.toolId IN 
	 (SELECT P.toolId
      FROM purchases AS P
      WHERE P.toolId=T.toolId
			AND P.customerId = 138);
            
# 5. A retrieval query using joined tables
# Getting all tools (from left table) and Power Type from right table, will show null if left not in right
SELECT T.tName, E.powerType 
FROM tool AS T 
LEFT OUTER JOIN electrical AS E 
ON T.toolId = E.toolId;

# 6. 
# 
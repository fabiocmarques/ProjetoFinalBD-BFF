SELECT programa.codprog, nomeprog
FROM programa
JOIN diaria ON diaria.codprog = programa.codprog
GROUP BY programa.codprog
ORDER BY COUNT(programa.codprog) DESC
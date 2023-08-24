SELECT * FROM db_calificaciones.alumnos;
SELECT * FROM db_calificaciones.materias;
SELECT * FROM db_calificaciones.calificaciones;
SELECT * FROM db_calificaciones.accesos;

SELECT
    MatriculaAlumno AS Matricula,
    NombreCompleto,
	ROUND(MAX(CASE WHEN ClaveMateria = 'ABD023' THEN ROUND(AVG((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) END), 1) AS ABD,
    ROUND(MAX(CASE WHEN ClaveMateria = 'DDI023' THEN ROUND(AVG((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) END), 1) AS DDI,
    ROUND(MAX(CASE WHEN ClaveMateria = 'DWI023' THEN ROUND(AVG((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) END), 1) AS DWI,
    ROUND(MAX(CASE WHEN ClaveMateria = 'DWP023' THEN ROUND(AVG((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) END), 1) AS DWP,
    ROUND(
        (MAX(CASE WHEN ClaveMateria = 'ABD023' THEN ROUND(AVG((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) END) +
         MAX(CASE WHEN ClaveMateria = 'DDI023' THEN ROUND(AVG((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) END) +
         MAX(CASE WHEN ClaveMateria = 'DWI023' THEN ROUND(AVG((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) END) +
         MAX(CASE WHEN ClaveMateria = 'DWP023' THEN ROUND(AVG((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) END) ) / 4, 1) AS Promedio
FROM (
    SELECT
        calificaciones.MatriculaAlumno,
        CONCAT(alumnos.Nombre, ' ', alumnos.Paterno, ' ', alumnos.Materno) AS NombreCompleto,
        calificaciones.ClaveMateria,
		ROUND(AVG((Parcial1 + Parcial2 + Parcial3) / 3), 1) AS Promedio
    FROM calificaciones
    LEFT JOIN alumnos ON calificaciones.MatriculaAlumno = alumnos.Matricula
) AS PivotData
GROUP BY MatriculaAlumno, NombreCompleto;

SELECT 
    a.Matricula, 
    CONCAT(a.Nombre, ' ', a.Paterno, ' ', a.Materno) AS NombreCompleto,
    MAX(CASE WHEN m.ClaveMateria = 'ABD023' THEN ROUND((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3, 1) END) AS ABD,
    MAX(CASE WHEN m.ClaveMateria = 'DDI023' THEN ROUND((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3, 1) END) AS DDI,
    MAX(CASE WHEN m.ClaveMateria = 'DWI023' THEN ROUND((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3, 1) END) AS DWI,
    MAX(CASE WHEN m.ClaveMateria = 'DWP023' THEN ROUND((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3, 1) END) AS DWP,
    ag.promedio AS Promedio
FROM 
    calificaciones c
JOIN 
    materias m ON c.ClaveMateria = m.ClaveMateria
JOIN 
    alumnos a ON c.MatriculaAlumno = a.Matricula
JOIN (
    SELECT 
        MatriculaAlumno, 
        ROUND(AVG((Parcial1 + Parcial2 + Parcial3) / 3), 1) AS promedio
    FROM 
        calificaciones
    GROUP BY 
        MatriculaAlumno
) ag ON a.Matricula = ag.MatriculaAlumno
GROUP BY 
    a.Matricula, ag.promedio;



SET @parcial_deseado = 2;

SELECT 
    a.Matricula,
    CONCAT(a.Nombre, ' ', a.Paterno, ' ', a.Materno) AS NombreCompleto,
    m.Nombre AS NombreMateria,
    CASE 
        WHEN @parcial_deseado = 1 THEN c.Parcial1
        WHEN @parcial_deseado = 2 THEN c.Parcial2
        WHEN @parcial_deseado = 3 THEN c.Parcial3
    END AS CalificacionParcial
FROM alumnos a
INNER JOIN calificaciones c ON a.Matricula = c.MatriculaAlumno
INNER JOIN materias m ON c.ClaveMateria = m.ClaveMateria
WHERE m.ClaveMateria = 'DWI023'; -- Reemplaza con la clave de la materia deseada

-- con esta consulta muestro la calificaciones de una materia
SELECT 
    a.Matricula,
    CONCAT(a.Nombre, ' ', a.Paterno, ' ', a.Materno) AS NombreCompleto,
    m.Nombre AS NombreMateria,
    c.Parcial1,
    c.Parcial2,
    c.Parcial3,
    ROUND(((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) AS Promedio,
    CASE 
        WHEN ROUND(((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) < 8 THEN 8
        ELSE c.Extraordinario
    END AS Extraordinario
FROM alumnos a
INNER JOIN calificaciones c ON a.Matricula = c.MatriculaAlumno
INNER JOIN materias m ON c.ClaveMateria = m.ClaveMateria
WHERE m.ClaveMateria = 'DWI023'; -- Reemplaza 'CLAVE_DE_LA_MATERIA' con la clave de la materia deseada

-- muestra calificaciones de los parciales y del promedio de las materias dependiendo del alumno
SELECT 
    a.Matricula,
    CONCAT(a.Nombre, ' ', a.Paterno, ' ', a.Materno) AS NombreCompleto,
    m.Nombre AS NombreMateria,
    c.Parcial1,
    c.Parcial2,
    c.Parcial3,
    ROUND(((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) AS Promedio,
    CASE 
        WHEN ROUND(((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) < 8 THEN 8
        ELSE c.Extraordinario
    END AS Extraordinario
FROM alumnos a
INNER JOIN calificaciones c ON a.Matricula = c.MatriculaAlumno
INNER JOIN materias m ON c.ClaveMateria = m.ClaveMateria
WHERE a.Matricula = '57221900133'; -- Reemplaza 'MATRICULA_DEL_ALUMNO' con la matrícula del alumno deseado

-- muestro las calificaciones de todas las materias 
SELECT 
    a.Matricula,
    CONCAT(a.Nombre, ' ', a.Paterno, ' ', a.Materno) AS NombreCompleto,
    m.Nombre AS NombreMateria,
    c.Parcial1,
    c.Parcial2,
    c.Parcial3,
    ROUND(((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) AS Promedio,
    CASE 
        WHEN ROUND(((c.Parcial1 + c.Parcial2 + c.Parcial3) / 3), 1) < 8 THEN 8
        ELSE c.Extraordinario
    END AS Extraordinario
FROM alumnos a
INNER JOIN calificaciones c ON a.Matricula = c.MatriculaAlumno
INNER JOIN materias m ON c.ClaveMateria = m.ClaveMateria;

SELECT 
    a.Matricula,
    CONCAT(a.Nombre, ' ', a.Paterno, ' ', a.Materno) AS NombreCompleto,
    m.Nombre AS NombreMateria,
    c.Parcial2
FROM alumnos a
INNER JOIN calificaciones c ON a.Matricula = c.MatriculaAlumno
INNER JOIN materias m ON c.ClaveMateria = m.ClaveMateria
WHERE m.ClaveMateria = 'DWI023'; -- Reemplaza con la clave de la materia deseadaSELECT
    MatriculaAlumno AS Matricula,
    NombreCompleto,
	ROUND(MAX(CASE WHEN ClaveMateria = 'ABD023' THEN Parcial1 END), 1) AS ABD,
    ROUND(MAX(CASE WHEN ClaveMateria = 'DDI023' THEN Parcial1 END), 1) AS DDI,
    ROUND(MAX(CASE WHEN ClaveMateria = 'DWI023' THEN Parcial1 END), 1) AS DWI,
    ROUND(MAX(CASE WHEN ClaveMateria = 'DWP023' THEN Parcial1 END), 1) AS DWP,
    ROUND(
        (MAX(CASE WHEN ClaveMateria = 'ABD023' THEN Parcial1 END) +
         MAX(CASE WHEN ClaveMateria = 'DDI023' THEN Parcial1 END) +
         MAX(CASE WHEN ClaveMateria = 'DWI023' THEN Parcial1 END) +
         MAX(CASE WHEN ClaveMateria = 'DWP023' THEN Parcial1 END) ) / 4, 1) AS Promedio
FROM (
    SELECT
        calificaciones.MatriculaAlumno,
        CONCAT(alumnos.Nombre, ' ', alumnos.Paterno, ' ', alumnos.Materno) AS NombreCompleto,
        calificaciones.ClaveMateria,
        calificaciones.Parcial1
    FROM calificaciones
    LEFT JOIN alumnos ON calificaciones.MatriculaAlumno = alumnos.Matricula
) AS PivotData
GROUP BY MatriculaAlumno, NombreCompleto;
SELECT
    MatriculaAlumno AS Matricula,
    NombreCompleto,
	ROUND(MAX(CASE WHEN ClaveMateria = 'ABD023' THEN Parcial1 END), 1) AS ABD,
    ROUND(MAX(CASE WHEN ClaveMateria = 'DDI023' THEN Parcial1 END), 1) AS DDI,
    ROUND(MAX(CASE WHEN ClaveMateria = 'DWI023' THEN Parcial1 END), 1) AS DWI,
    ROUND(MAX(CASE WHEN ClaveMateria = 'DWP023' THEN Parcial1 END), 1) AS DWP,
    ROUND(
        (MAX(CASE WHEN ClaveMateria = 'ABD023' THEN Parcial1 END) +
         MAX(CASE WHEN ClaveMateria = 'DDI023' THEN Parcial1 END) +
         MAX(CASE WHEN ClaveMateria = 'DWI023' THEN Parcial1 END) +
         MAX(CASE WHEN ClaveMateria = 'DWP023' THEN Parcial1 END) ) / 4, 1) AS Promedio
FROM (
    SELECT
        calificaciones.MatriculaAlumno,
        CONCAT(alumnos.Nombre, ' ', alumnos.Paterno, ' ', alumnos.Materno) AS NombreCompleto,
        calificaciones.ClaveMateria,
        calificaciones.Parcial1
    FROM calificaciones
    LEFT JOIN alumnos ON calificaciones.MatriculaAlumno = alumnos.Matricula
) AS PivotData
GROUP BY MatriculaAlumno, NombreCompleto;

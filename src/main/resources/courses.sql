SELECT DISTINCT teachers.first_name, teachers.last_name, teachers.second_name, groups_st.title FROM teachers 
JOIN subjects ON teachers.id = subjects.teacher_id 
JOIN subject_group ON subjects.subject_id = subject_group.subjects_id
JOIN groups_st ON subject_group.groups_st_specialty = groups_st.group_id 
ORDER BY teachers.second_name;

SELECT DISTINCT groups_st.specialty, subjects.title, subjects.`type`, subjects.hours FROM groups_st 
JOIN subject_group ON groups_st.group_id = subject_group.groups_st_specialty 
JOIN subjects ON subject_group.subjects_id = subjects.subject_id;

SELECT DISTINCT teachers.last_name, subjects.`type`, subjects.hours, subjects.cost_per_hour FROM teachers
JOIN subjects ON teachers.id = subjects.teacher_id;

SELECT DISTINCT teachers.last_name, subjects.`type`, subjects.hours, subjects.cost_per_hour FROM teachers
JOIN subjects ON teachers.id = subjects.teacher_id WHERE teachers.last_name='Pensov';

SELECT distinct teachers.first_name AS NAME, teachers.last_name AS LastName from teachers LEFT JOIN subjects on teachers.id = subjects.teacher_id 
WHERE teachers.EXP > 5 AND (subjects.title = 'Математика' OR subjects.title = 'Информатика');
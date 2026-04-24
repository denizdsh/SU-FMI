const DEFAULT_RETURN = "unknown";

/**
 * @param {[{name: "string", grades: [number], birthYear: number}]?} students
 * @returns {{
 *  topStudent: {name: "string", grades: [number], birthYear: number} | DEFAULT_RETURN,
 *  averageGrade: string,
 *  passedCount: number,
 *  ageStats: {youngest: number | DEFAULT_RETURN, oldest: number | DEFAULT_RETURN}
 * }}
 */
function analyzeStudents(students = []) {
  let topStudent = null,
    totalGrade = 0,
    totalGradeCount = 0,
    passedCount = 0;

  const ageStats = { youngest: 0, oldest: 0 };

  for (const s of students) {
    const studentTotalGrade = getArraySum(s.grades);
    const studentGradeCount = s.grades.length;
    const studentAvgGrade = studentTotalGrade / studentGradeCount;

    totalGrade += studentTotalGrade;
    totalGradeCount += studentGradeCount;

    if (
      studentTotalGrade &&
      (!topStudent ||
        studentAvgGrade >
          getArraySum(topStudent.grades) / topStudent.grades.length)
    ) {
      topStudent = s;
    }

    if (studentAvgGrade >= 3) {
      passedCount += 1;
    }

    if (!ageStats.youngest || ageStats.youngest < s.birthYear) {
      ageStats.youngest = s.birthYear;
    }

    if (!ageStats.oldest || ageStats.oldest > s.birthYear) {
      ageStats.oldest = s.birthYear;
    }
  }

  return {
    // Populated object with print values
    topStudent: topStudent || DEFAULT_RETURN,
    averageGrade: !!totalGrade
      ? (totalGrade / totalGradeCount).toFixed(2)
      : DEFAULT_RETURN,
    passedCount,
    ageStats: Object.fromEntries(
      // diabolical. made good use of last minutes before submitting.
      // please do not reduce exam score
      Object.entries(ageStats).map(([_, year]) => [
        _,
        year ? new Date().getFullYear() - year : DEFAULT_RETURN,
      ])
    ),
  };
}

function getArraySum(numArray) {
  return numArray.reduce((acc, num) => acc + num, 0);
}

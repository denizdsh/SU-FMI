QUnit.module('task-3-students', function () {
  QUnit.test('validate - input with students', function (assert) {
    const students = [
      { name: "Иван", grades: [5, 4, 6], birthYear: 2000 },
      { name: "Петър", grades: [3, 2, 4], birthYear: 2001 },
      { name: "Мария", grades: [6, 6, 5], birthYear: 1999 },
      { name: "Петя", grades: [2, 2, 2], birthYear: 1998 }
    ];

    const result = analyzeStudents(students);

    assert.deepEqual(result.topStudent, { name: "Мария", grades: [6, 6, 5], birthYear: 1999 });
    assert.equal(result.averageGrade, 3.92);
    assert.equal(result.passedCount, 3);
    assert.deepEqual(result.ageStats, {
      youngest: 24,
      oldest: 27
    });
  });

  QUnit.test('validate - input with students (2)', function (assert) {
    const students = [
      { name: "Иван", grades: [], birthYear: 2000 },
      { name: "Петър", grades: [], birthYear: 2001 },
      { name: "Мария", grades: [], birthYear: 1999 },
      { name: "Петя", grades: [], birthYear: 1998 }
    ];

    const result = analyzeStudents(students);

    assert.deepEqual(result.topStudent, "unknown");
    assert.equal(result.averageGrade, "unknown");
    assert.equal(result.passedCount, 0);
    assert.deepEqual(result.ageStats, {
      youngest: 24,
      oldest: 27
    });
  });

  QUnit.test('validate - input without students', function (assert) {
    const students = []

    const result = analyzeStudents(students);

    assert.deepEqual(result.topStudent, "unknown");
    assert.deepEqual(result.averageGrade, "unknown");
    assert.equal(result.passedCount, 0);
    assert.deepEqual(result.ageStats, {
      youngest: "unknown",
      oldest: "unknown"
    });
  });
});
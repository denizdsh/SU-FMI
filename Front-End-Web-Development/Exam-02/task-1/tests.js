QUnit.module('task-1-students', function () {
  QUnit.test('validate - uppercase', function (assert) {
    assert.equal(formatText("Hello World", { uppercase: true }), "HELLO WORLD");
  });
  QUnit.test('validate - removeSpaces, reverse', function (assert) {
    assert.equal(formatText("Hello World", { removeSpaces: true, reverse: true }), "dlroWolleH");
    assert.equal(formatText("Hello World", { maxLength: 3 }), "Hel...");
    assert.equal(formatText(123, {}), "");
    assert.equal(formatText("Hello World", { uppercase: true, maxLength: 4 }), "HELL...");
  });
  QUnit.test('validate - maxLength', function (assert) {
    assert.equal(formatText("Hello World", { maxLength: 3 }), "Hel...");
  });
  QUnit.test('validate - no options', function (assert) {
    assert.equal(formatText(123, {}), "");
  });
  QUnit.test('validate - uppercase, maxLength', function (assert) {
    assert.equal(formatText("Hello World", { uppercase: true, maxLength: 4 }), "HELL...");
  });
});
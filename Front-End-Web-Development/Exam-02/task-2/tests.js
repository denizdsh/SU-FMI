QUnit.module('task-2-students', function () {
  QUnit.test('validate - increment', function (assert) {
    const counter = createCounter(10, 5);

    assert.equal(counter.increment(), 15);
    assert.equal(counter.getValue(), 15);
  });

  QUnit.test('validate - getValue', function (assert) {
    const counter = createCounter(10, 5);

    assert.equal(counter.getValue(), 10);
    assert.equal(counter.getValue(), 10);
  });

  QUnit.test('validate - increment', function (assert) {
    const counter = createCounter(10, 5);

    assert.equal(counter.decrement(), 5);
    assert.equal(counter.getValue(), 5);
  });

  QUnit.test('validate - reset', function (assert) {
    const counter = createCounter(10, 5);

    assert.equal(counter.decrement(), 5);
    assert.equal(counter.getValue(), 5);

    assert.equal(counter.reset(), 10);
    assert.equal(counter.getValue(), 10);
  });

  QUnit.test('validate - defaults', function (assert) {
    const counter = createCounter();

    assert.equal(counter.decrement(), -1);
    assert.equal(counter.getValue(), -1);

    assert.equal(counter.reset(), 0);
    assert.equal(counter.getValue(), 0);

    assert.equal(counter.increment(), 1);
    assert.equal(counter.getValue(), 1);
  });
});
/**
 * @returns {{increment: Function, decrement: Function, getValue: Function, reset: Function}}
 */
function createCounter(start = 0, step = 1) {
  start = +start;
  step = +step;

  if (isNaN(start) || isNaN(step)) {
    throw "No no no!";
  }

  let value = start;

  return {
    increment: () => (value += step),
    decrement: () => (value -= step),
    getValue: () => value,
    reset: () => (value = start),
  };
}

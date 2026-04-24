/**
 * @param {string} text
 * @param {{uppercase?: boolean, removeSpaces?: boolean, reverse?: boolean, maxLength?: number}?} options
 * @returns {string}
 */
function formatText(text, options) {
  text = validateText(text);
  if (!text) {
    return "";
  }

  options = {
    uppercase: false,
    removeSpaces: false,
    reverse: false,
    ...options,
  };

  const formatter = {
    uppercase: uppercase,
    removeSpaces: removeSpaces,
    reverse: reverse,
    maxLength: maxLength,
  };

  Object.keys(formatter).forEach((k) => {
    // iterating as to keep operations ordered
    if (!options[k]) {
      return;
    }

    text = formatter[k](text, options[k]);
  });

  return text;
}

function validateText(text) {
  return text && typeof text === "string" ? text : "";
}

function uppercase(text) {
  return validateText(text).toLocaleUpperCase();
}

function removeSpaces(text) {
  return validateText(text).split(/\s/).join("");
}

function reverse(text) {
  return [...validateText(text)].reverse().join("");
}

function maxLength(text, length) {
  if (typeof length !== "number" || isNaN(length)) {
    return text;
  }

  text = validateText(text);
  length = Math.abs(length);
  return text.slice(0, length) + (text.length > length ? "..." : "");
}

console.log(formatText("Hello World", { uppercase: true }));
// "HELLO WORLD"

console.log(formatText("Hello World", { removeSpaces: true, reverse: true }));
// "dlroWolleH"

console.log(formatText("Hello World", { maxLength: 3 }));
// "Hel..."

console.log(formatText(123, {}));
// ""

console.log(formatText("Hello World", { uppercase: true, maxLength: 4 }));
// "HELL..."

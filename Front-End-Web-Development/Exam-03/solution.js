// Constants
const DEFAULT_BG = "#fafafa";

// Retrieve elements
const moveRBtn = document.querySelector(".move-right");
const moveLBtn = document.querySelector(".move-left");
const lists = document.getElementsByClassName("list");

// The point of not giving it a name/id/class was to probably
// to make us retrieve element by going to event target parent and browsing its children nodes...
// But I think this is more consistent and easy to read and manage if any recompositions are made
const addInput = document.querySelector(
  ".input-group:has(+ #add-button) > input"
);
const addBtn = document.getElementById("add-button");

const sortAscBtn = document.querySelector("button[data-sort=ascending]");
const sortDescBtn = document.querySelector("button[data-sort=descending]");

const removeDupsBtn = document.querySelector("button[remove-duplicates]");

const bgInput = document.getElementById("bg-color-input");
const applyBgBtn = document.getElementById("apply-bg-button");
let currBgValue = DEFAULT_BG;

const deleteBtn = document.querySelector("[data-button=delete-items]");

function getListItems(checked = false) {
  // Easier than filtering by value, since using querySelector anyway
  const [leftListItems, rightListItems] = Array.from(lists).map((l) =>
    l.querySelectorAll(
      `.list-item:has(input[type=checkbox]${checked ? ":checked" : ""})`
    )
  );

  return { leftListItems, rightListItems };
}

// Add event listeners
addBtn.addEventListener("click", addHandler);

moveRBtn.addEventListener("click", moveRightHandler);
moveLBtn.addEventListener("click", moveLeftHandler);

sortAscBtn.addEventListener("click", sortAscHandler);
sortDescBtn.addEventListener("click", sortDescHandler);

removeDupsBtn.addEventListener("click", removeDupsHandler);

applyBgBtn.addEventListener("click", applyRightListItemsBgHandler);

deleteBtn.addEventListener("click", deleteRightListItemsHandler);

// Handlers
function addHandler() {
  // Validate
  const value = addInput.value.trim();

  if (!value) {
    return;
  }

  const { leftListItems, rightListItems } = getListItems();

  if (leftListItems.length + rightListItems.length >= 10) {
    return;
  }

  // Add action
  const leftList = getLeftList();

  const li = document.createElement("li");
  li.className = "list-item";
  const input = document.createElement("input");
  input.type = "checkbox";
  const label = document.createElement("label");
  label.textContent = value;

  li.appendChild(input);
  li.appendChild(label);

  leftList.appendChild(li);
}

function moveHandler(checkedItems, targetList) {
  if (checkedItems.length < 1) {
    return;
  }

  uncheckInputs();

  for (const item of checkedItems) {
    item.remove();
    targetList.appendChild(item);
  }
}

function moveRightHandler() {
  const { leftListItems } = getListItems(true);
  moveHandler(leftListItems, getRightList());
}

function moveLeftHandler() {
  const { rightListItems } = getListItems(true);
  moveHandler(rightListItems, getLeftList());
}

function sortHandler(ascending = true) {
  const rightList = getRightList();
  const { rightListItems } = getListItems();

  const sorted = Array.from(rightListItems).sort((liA, liB) => {
    const a = liA.querySelector("label").textContent;
    const b = liB.querySelector("label").textContent;

    console.log("a ", a, " b ", b);

    return ascending ? a - b : b - a;
  });

  rightList.replaceChildren(...sorted);
}

function sortAscHandler() {
  sortHandler();
}

function sortDescHandler() {
  sortHandler(false);
}

function removeDupsHandler() {
  const { rightListItems } = getListItems();
  const rlValues = Array.from(rightListItems).map(
    (rli) => rli.querySelector("label").textContent
  );

  const values = [];
  const uniqueItems = [];

  for (let idx = 0; idx < rlValues.length; idx++) {
    if (!values.includes(rlValues[idx])) {
      values.push(rlValues[idx]);
      uniqueItems.push(rightListItems.item(idx));
    }
  }

  const rightList = getRightList();
  rightList.replaceChildren(...uniqueItems);
}

function applyRightListItemsBgHandler() {
  currBgValue = bgInput.value;

  updateListItemsBg();
}

function deleteRightListItemsHandler() {
  const rightList = getRightList();

  rightList.replaceChildren();
}

// Helpers
function getLeftList() {
  return lists.item(0);
}

function getRightList() {
  return lists.item(1);
}

function uncheckInputs() {
  const liList = getListItems(true);
  for (const listKey in liList) {
    for (const li of liList[listKey]) {
      li.querySelector("input").checked = false;
    }
  }
}

function updateListItemsBg() {
  const { rightListItems } = getListItems();

  for (const rli of rightListItems) {
    rli.style.backgroundColor = currBgValue;
  }
}

# Задача 1: Работа със стрингове и методи

## Условие:
Създайте функция `formatText`, която форматира текст според подадените опции.

### Параметри:
- `text` - текстът за форматиране (ако не е string, връща празен стринг)
- `options` - обект с опции за форматиране (по подразбиране празен обект)

### Опции за форматиране:
- **`uppercase`** - преобразува към главни букви (по подразбиране: `false`)
- **`removeSpaces`** - премахва всички интервали (по подразбиране: `false`) 
- **`reverse`** - обръща реда на символите (по подразбиране: `false`)
- **`maxLength`** - ограничава дължината и добавя "..." ако е необходимо (по подразбиране: без ограничение)

**Забележка:** Операциите се прилагат в следния ред:
1. Валидация на входа
2. uppercase
3. removeSpaces
4. reverse
5. maxLength

### Тестови случаи:
```javascript
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
```
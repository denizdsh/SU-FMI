# Задача 2: Функции и scope

## Условие:
Създайте функция `createCounter`, която връща обект с методи за работа с брояч (числата не е задължително да бъдат цели).

### Параметри:
- `start` (по подразбиране 0) - начална стойност на брояча
- `step` (по подразбиране 1) - стъпка за увеличаване/намаляване

### Върнатият обект трябва да има методи:
- **`increment()`** - увеличава текущата стойност със `step` и връща новата стойност
- **`decrement()`** - намалява текущата стойност със `step` и връща новата стойност  
- **`getValue()`** - връща текущата стойност без да я променя
- **`reset()`** - възстановява началната стойност и я връща

### Тестови случаи:
```javascript
const counter = createCounter(10, 5);
console.log(counter.increment()); // 15
console.log(counter.getValue());  // 15
console.log(counter.decrement()); // 10
console.log(counter.reset());     // 10
console.log(counter.getValue());  // 10

const defaultCounter = createCounter();
console.log(defaultCounter.increment()); // 1
console.log(defaultCounter.decrement()); // 0
```
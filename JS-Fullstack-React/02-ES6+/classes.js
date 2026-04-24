"use strict";
/*
    Call stack demo
*/

class Person {
  // static nextId = 0;

  constructor(name, age, address) {
    this.name = name;
    this.age = age;
    this.address = address;
    // this.id = ++Person.nextId;
  }

  toString() {
    return `Person ${this.name} age: ${this.age}, address: ${this.address}`;
  }
}

function main() {
  (async function () {
    // Does not block the call stack
    const promisedPerson = new Promise((resolve) => {
      resolve({
        name: "Promise Skywalker",
        birth_year: "2",
        homeworld: "Promised land",
      });
    });

    promisedPerson
      .then((data) => {
        console.log("Promise resolved");
        return data;
      })
      .then((data) => {
        const person = new Person(data.name, data.birth_year, data.homeworld);
        console.log(person);
      });

    // await promisedPerson; <- will block all operations below

    // Does not get blocked by the non-awaited promises above
    const person1 = new Person("Sync Skywalker", "1", "Sync land");
    console.log(person1);

    // Blocks the call stack in the parent async function until the promise is resolved
    const res = await fetch("https://swapi.dev/api/people/1");
    const data2 = await res.json();
    console.log("Fetch resolved");
    const person2 = new Person(data2.name, data2.birth_year, data2.homeworld);
    console.log(person2);

    // Gets blocked by awaited promise
    const person3 = new Person("Leia Organa", "4", "Sync after async land");
    console.log(person3);
  })();

  console.log("Call stack finish");
}

main();

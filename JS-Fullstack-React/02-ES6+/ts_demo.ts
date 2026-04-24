type ExtractNumberType<T> = T extends `${infer U}`
  ? U extends `${number}`
    ? U
    : never
  : never;

function test<T extends `${number}`>(num: ExtractNumberType<T>) {
  console.log(+num);
}

test("231");
// test("231s"); // Error: Argument of type '"231s"' is not assignable to parameter of type 'ExtractNumberType<"231s">'.

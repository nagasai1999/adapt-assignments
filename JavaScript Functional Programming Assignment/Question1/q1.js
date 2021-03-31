/*Make a function called composedValue that takes two functions f1 and f2 and a value and returns
f1(f2(value)), i.e., the first function called on the result of the second function called on the value.
function square(x) { return(x*x); }
function double(x) { return(x*2); }
composedValue(square, double, 5); --> 100 // I.e., square(double(5))
*/

//Arrow function
const composedValue = (f1, f2, value) => () => f1(f2(value));

//Normal way
const composedValueNormal = function (f1, f2, value) {
  return function () {
    return f1(f2(value));
  };
};

function square(x) {
  return x * x;
}
function double(x) {
  return x * 2;
}

const getNumber = composedValue(square, double, 5);
const getNumberNormal = composedValueNormal(square, double, 5);

console.log("Result with Array Function : " + getNumber()); // I.e., square(double(5))
console.log("Result with Traditional Way : " + getNumberNormal()); // I.e., square(double(5))

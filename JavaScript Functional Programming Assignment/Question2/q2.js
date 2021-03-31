/*Make a function called compose that takes two functions f1 and f2 and returns a new function that,
when called on a value, will return f1(f2(value)). Assume that f1 and f2 each take exactly one argument.
var f1 = compose(square, double);
f1(5); --> 100
f1(10); --> 400
var f2 = compose(double, square);
f2(5); --> 50
f2(10); --> 200
*/

const compose = (f1, f2) => (value) => f1(f2(value));

const square = (x) => x * x;
const double = (x) => x * 2;

var f1 = compose(square, double);
console.log(f1(5));
console.log(f1(10));
var f2 = compose(double, square);
console.log(f2(5));
console.log(f2(10));

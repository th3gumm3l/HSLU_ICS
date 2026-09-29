onmessage = function (event) {
  let work = event.data;
  let res = fib(Number(work));
  postMessage("fib(" + work + ") = " + res);
}

function fib(n) {
  let res;
  if (n == 1 || n == 2) {
    res = 1;
  } else {
    res = fib(n - 1) + fib(n - 2);
  }

  return res;
}

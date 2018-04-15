
arr1 = ['A', 'B', 'C'];
arr2 = [1,2,3,4,5,6,7,8,9];
arr3 = [3,5,1,2,7,9,6,8];


r = arr1.filter(function (element, index, self) {
    console.log(element);
    console.log(index);
    console.log(self);
    return true;
});


product = function (arr) {
    arr.reduce(function (x, y) {
        return x * y;
    })
};
console.log(product(arr2));


arr3.sort(function (x, y) {
    if(x < y) {return 1;}
    if(x > y) {return -1;}
    return 0;
});
console.log(arr3);


function sum(arr) {
    return arr.reduce(function (x, y) {
        return x + y;
    })
}
console.log(sum(arr2));


function lazy_sum(arr) {
    return () => arr.reduce(function (x, y) {
        return x + y;
    });
}

const f = lazy_sum(arr2);
console.log(f());


function* fib(max) {
    let t,
        a = 0,
        b = 1,
        n = 0;
    while (n < max) {
        yield a;
        [a, b] = [b, a + b];
        n++;
    }
}
const f_fib = fib(5);
console.log(f_fib.next());
console.log(f_fib.next());
console.log(f_fib.next());
console.log(f_fib.next());
console.log(f_fib.next());
console.log(f_fib.next());


class Animal {
    constructor(name) {
        this.name = name;
    }
}

class Cat extends Animal {
    constructor(name) {
        super(name);
    }

    say() {
        console.log('Hello, ' + this.name + '!');
    }
}

const cat = new Cat('哆菲猫');
cat.say();

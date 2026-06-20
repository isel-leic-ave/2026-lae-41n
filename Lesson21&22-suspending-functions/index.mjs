function* foo() {
    console.log(`Generating 19`)
    yield 19
    console.log(`Generating 7`)
    yield 7
    console.log(`Generating 11`)
    yield 11
    console.log("END")
    return
    yield 5
}

function* fibonacci() {
    let prev = 0;
    let next = 1;
    while (true) {
        yield prev;
        const aux = prev + next;
        prev = next;
        next = aux;
    }
}

const iter = foo()
let item = iter.next()
console.log(item.value)
item = iter.next()
console.log(item.value)

let i = 0;

// for (const x of fibonacci()) {
//     console.log(x);
//     if (++i === 10) break;
// }

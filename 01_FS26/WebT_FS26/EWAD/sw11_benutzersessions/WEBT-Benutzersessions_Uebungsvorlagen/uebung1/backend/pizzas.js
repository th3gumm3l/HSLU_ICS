import express from 'express'
const router = express.Router();

// constants
const pizzaTypes = [ 'Margherita', 'Fantasia', 'Tonno'];
const pizzas = [ {name: 'Margherita', price: 17.5}, {name: 'Fantasia', price: 19.5}, {name: 'Tonno', price: 18.5}];

// updates basket (increment or decrement quantity for pizza)
function updateBasket(req, res, pizza, update) {
    let quantity = 0;
    if (pizza in req.cookies && parseInt(req.cookies[pizza]) > 0) {
        quantity = parseInt(req.cookies[pizza]);
    }

    quantity += update;
    if (quantity < 0) quantity = 0;

    if (quantity === 0) {
        res.clearCookie(pizza);
    } else {
        res.cookie(pizza, quantity);
    }
}

// merges values from cookies (quantities in basket) and static data 
// (pizza, prices) and returns the result in an associative array 
function getBasket(req) {
    const basket = {
        pizzas: [],
        grandtotal: 0
    };
    for (let i = 0; i < pizzas.length; ++i) {
        const name = pizzas[i].name;
        const price = pizzas[i].price;
        
        let quantity = 0;
        if (name in req.cookies && parseInt(req.cookies[name]) > 0) {
            quantity = parseInt(req.cookies[name]);
        }

        basket.pizzas.push({
            name: name,
            price: price,
            quantity: quantity,
            total: price * quantity
        });
        basket.grandtotal += price * quantity;
    }
    return basket;
}

router.post('/' ,function(req, res) {
    res.type('application/json');
    let body;
    try {
        body = JSON.parse(req.body.toString('utf-8'));
        if (body == null) throw Error('body muss not be empty');
        if (!('update' in body)) throw Error('property "update" is missing');    
        const updateInt = parseInt(body.update);
        if (updateInt < -1 || updateInt > 1) throw Error('update must be integer in range [-1,1]');
        if (!('pizza' in body)) throw Error('property "pizza" is missing');
        if (!pizzaTypes.includes(body.pizza)) throw Error('unknown pizza type');
    } catch (error) {
        res.status(400);
        res.send(error.message);
        return;
    }
    try {
        updateBasket(req, res, body.pizza, parseInt(body.update));
    } catch (error) {
        console.log(error);
        res.status(500);
        res.send('internal server error');
        return;
    }
    res.send();
});

router.get('/', function(req, res) {
    res.type('application/json');
    res.send(JSON.stringify(getBasket(req)));
});

export default router;

import React from 'react';
import Cart from './Cart';

export class OnlineShopping extends React.Component {
  render() {
    const CartInfo = [
      { itemname: "Laptop", price: 85000 },
      { itemname: "Smart TV", price: 95000 },
      { itemname: "Microwave Oven", price: 18000 },
      { itemname: "Smartphone", price: 42000 },
      { itemname: "Air Conditioner", price: 55000 }
    ];
    return (
      <div className="mydiv">
        <h1>Items Ordered :</h1>
        <Cart item={CartInfo} />
      </div>
    );
  }
}

export default OnlineShopping;

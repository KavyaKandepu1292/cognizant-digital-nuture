function FlightDetails(props) {
  const flights = [
    { flightNo: 'AI-330', from: 'Hyderabad', to: 'Delhi', departure: '05:45 AM', price: 4900 },
    { flightNo: 'IN-512', from: 'Hyderabad', to: 'Mumbai', departure: '11:00 AM', price: 3800 },
    { flightNo: 'SG-227', from: 'Hyderabad', to: 'Bangalore', departure: '03:30 PM', price: 2600 }
  ];

  return (
    <div>
      <h2>Available Flights</h2>
      <table>
        <thead>
          <tr>
            <th>Flight No</th>
            <th>From</th>
            <th>To</th>
            <th>Departure</th>
            <th>Price</th>
          </tr>
        </thead>
        <tbody>
          {flights.map((flight) => (
            <tr key={flight.flightNo}>
              <td>{flight.flightNo}</td>
              <td>{flight.from}</td>
              <td>{flight.to}</td>
              <td>{flight.departure}</td>
              <td>Rs. {flight.price}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <p><i>Please login to book tickets.</i></p>
    </div>
  );
}

export default FlightDetails;
